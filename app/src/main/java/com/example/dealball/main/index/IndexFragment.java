package com.example.dealball.main.index;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.InfoWindow;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.dealball.R;
import com.example.dealball.main.base.BaseFragment;
import com.example.dealball.main.bean.MyInfoBean;
import com.example.dealball.main.bean.PostBean;
import com.example.dealball.main.mine.settings.SettingsActivity;
import com.example.dealball.main.utils.ImageUtil;
import com.example.dealball.main.utils.MyApplication;
import com.example.dealball.main.utils.Utility;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class IndexFragment extends BaseFragment implements  BaiduMap.OnMarkerClickListener, BaiduMap.OnMyLocationClickListener{
    private LocationClient mLocationClient;

    //    private TextView positionText;
    private MapView mapView; //百度地图控件

    private BaiduMap baiduMap; //百度地图对象
    private boolean isFirstLocate = true;
//    private boolean isPicOk = false;

    private static OverlayManager overlayManager;
    private MyOverlayOption myOverlayOption;

    private InfoWindow infoWindow;
    private View infoView;
    private View view;
    private static BDLocation myPosition;
    private BitmapDescriptor bitmapDescriptor;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mLocationClient = new LocationClient(MyApplication.getContext());
        mLocationClient.registerLocationListener(new MyLocationListener());
        SDKInitializer.initialize(MyApplication.getContext());     //初始化SDK
        View indexView = inflater.inflate(R.layout.index_fragment, container, false);
        mapView = indexView.findViewById(R.id.bmapView);
        baiduMap = mapView.getMap();

//        baiduMap.setMyLocationEnabled(true);  //开启定位图层

//        positionText = indexView.findViewById(R.id.position_text_view);

        TextView title = indexView.findViewById(R.id.tv_title);
        title.setText("首页");
        initData();

        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        if (!permissionList.isEmpty()) {
            String[] permissions = permissionList.toArray(new String[permissionList.size()]);
            IndexFragment.this.requestPermissions(permissions, 1);

        } else {
            requestLocation();
        }
        initEvent();


        return indexView;
    }

    private void initData() {
        view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.map_view, null);
        bitmapDescriptor = BitmapDescriptorFactory.fromView(view);
    }

    private void initEvent() {
        baiduMap.setOnMarkerClickListener(this);
        baiduMap.setOnMyLocationClickListener(this);
    }

    @Override
    public void onStart() {
        super.onStart();

    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
        if(isLog()){
            initLocationMarkInfos();
        }
        System.out.println("onResume 先走");
    }


    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {     //登录之后才能看见发帖
        if(isVisible){
            System.out.println("onFragmentVisibleChange 先走1");

            if(Utility.getPost()){       //本来不应该这样设置的，应是登录了就可以点击地图上已有的Marker，但是目前还没有加载所有的Marker容器，而notifyDataSetChanged()需要有发布帖子需要有数据
                notifyDataSetChanged();
                Utility.setPost(false);
            }
            isFirstLocate = true;
            System.out.println("onFragmentVisibleChange 先走2");
        }
    }
    private void notifyDataSetChanged() {
        String avatar = DataSupport.select("avatar").where("userId = ?", String.valueOf(Utility.getMyInfoBean().getUserId())).find(MyInfoBean.class).get(0).getAvatar();
        PostBean postBean = new PostBean(Utility.getPromise().getPromiseId(), avatar, Utility.getPromise().getLongitude(),Utility.getPromise().getLatitude() );//将新增的发帖添加到数据库
        postBean.saveOrUpdate(postBean.getPromiseId());
    }


    @Override
    public boolean onMarkerClick(Marker marker) {

          Bundle bundle = marker.getExtraInfo();
          int promiseId  = bundle.getInt("promiseId"); //根据发帖的编号具体判断是哪一个marker

              if(promiseId != 0){     //默认的就可以触发，自己定义的marker就触发不了，解决这个问题 猜想：应该是图片大小的问题，导致触发点没有了
                infoView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.info_window, null);
                infoWindow = new InfoWindow(getInfoView(marker, promiseId), marker.getPosition(), -107); //第一个参数的使用是InfoWindow自定义View点击事件能响应的关键
                baiduMap.showInfoWindow(infoWindow);
            }
        return true;
    }

    @Override
    public boolean onMyLocationClick() {
        LatLng ll=new LatLng(myPosition.getLatitude(),myPosition.getLongitude());
        infoView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.info_window, null);
        infoWindow = new InfoWindow(getInfoView(), ll, -107); //第一个参数的使用是InfoWindow自定义View点击事件能响应的关键
        baiduMap.showInfoWindow(infoWindow);
        return true;
    }

    private View getInfoView(final Marker marker, final int promiseId){
        if(infoView == null){
            infoView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.info_window, null);
        }
        LinearLayout ll_info_detail = infoView.findViewById(R.id.ll_info_detail);
        ll_info_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            /*    Bundle bundle = new Bundle();
                bundle.putInt("promiseId", promiseId);*/
                Intent intent = new Intent(getActivity(), IndexActivity.class);//在第二个界面中 通过发帖的promiseId来从数据库中找出此发帖的详细信息
                intent.putExtra("promiseId", promiseId);
                startActivity(intent);

            }
        });
        infoView.findViewById(R.id.ll_info_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        return  infoView;
    }

    private View getInfoView(){
        if(infoView == null){
            infoView = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.info_window, null);
        }
        LinearLayout ll_info_detail = infoView.findViewById(R.id.ll_info_detail);
        ll_info_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putInt("promiseId", 0);//用0表示自己的发帖
                Intent intent = new Intent(getActivity(), IndexActivity.class);//点击自己的位置，在另一个界面查看自己发帖的详情
                intent.putExtra("bundlePromiseId", bundle);
                startActivity(intent);
            }
        });
        infoView.findViewById(R.id.ll_info_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
        return  infoView;
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
        mapView.onDestroy();
//        baiduMap.setMyLocationEnabled(false);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,String[] permissions,int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        switch (requestCode){
            case 1:
                if(grantResults.length>0){
                    for(int result:grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(getActivity(),"必须同意所有权限才能使用本程序",Toast.LENGTH_SHORT).show();

                            return;
                        }
                    }
                    requestLocation();
                }else {
                    Toast.makeText(getActivity(),"发生未知错误",Toast.LENGTH_SHORT).show();

                }
                break;
            default:
        }
    }
    private void requestLocation(){
        initLocation();
        mLocationClient.start();
    }
    private void initLocation(){
        LocationClientOption option=new LocationClientOption(); //定位相关的设置
        option.setScanSpan(5000);
        option.setIsNeedAddress(true);
        mLocationClient.setLocOption(option);
    }

    private void navigateTo(BDLocation location){

        if(isFirstLocate){
            LatLng ll=new LatLng(location.getLatitude(),location.getLongitude());//LatLng是一个类，构造方法接收2个参数，存放经纬度值。
            /*MapStatusUpdate update= MapStatusUpdateFactory.newLatLng(ll);//newLatLng是一个方法，返回一个MapStatusUpdate对象
            baiduMap.animateMapStatus(update);//animateMapStatus方法，采用动画的方式更新地图状态。
            update=MapStatusUpdateFactory.zoomTo(18f);*/
            MapStatusUpdate mapStatusUpdate = MapStatusUpdateFactory.newLatLngZoom(ll, 18f);
            baiduMap.animateMapStatus(mapStatusUpdate);
            isFirstLocate=false;
        }
        myPosition = location;
//        initLocationMarkInfos(); 时间每5ms地图更新一次


      /*  MyLocationData.Builder locationBuilder=new MyLocationData.Builder(); 不设置图层信息
        locationBuilder.latitude(location.getLatitude());
        locationBuilder.longitude(location.getLongitude());
        MyLocationData locationData=locationBuilder.build();
        baiduMap.setMyLocationData(locationData);*/

        /**
         * 在地图上展示一张默认图标
         * 在这里解决为什么定位的精度是6位小数点，导致定位有偏差  猜想：坐标系的问题 可以返回摩卡托坐标 也可以返回经纬度坐标 还可以...
         */

    }

    //初始化位置标注信息
    private void initLocationMarkInfos() {
        baiduMap.clear();
        List<PostBean> postBeanList = DataSupport.findAll(PostBean.class);
        //不设置显示不出带有头像的Marker

        for (int i = 0; i < postBeanList.size(); i++) {
            if (postBeanList.get(i) == null) {
                continue;
            }
            //创建一个BitmapDescriptor数组
            final BitmapDescriptor[] pic = {null};
            final PostBean postBean = postBeanList.get(i);
            String avatar = postBeanList.get(i).getAvatar();
            //判断头像地址是否为空,不为空就将地址传递过去加载到布局中
            if(avatar != null){
                returnPictureView(postBean, avatar, new ResultListener() {
                    @Override
                    public void onReturnResult(View view) {
                        pic[0] = BitmapDescriptorFactory.fromView(view);
                        putDataToMarkerOptions(pic[0], postBean);
                    }
                });
            } else {
                //头像地址为空就加载本地图片
                pic[0] = BitmapDescriptorFactory.fromResource(R.mipmap.face);
                putDataToMarkerOptions(pic[0], postBean);
            }
        }
    }

    //将图片加载到布局中
    private void returnPictureView(PostBean postBean, String avatar, final ResultListener resultListener) {
        final View markerView = LayoutInflater.from(getActivity()).inflate(R.layout.map_view, null);
        final CircleImageView map_portrait = markerView.findViewById(R.id.map_portrait);
         Bitmap bitmap = ImageUtil.getPictureFromBytes(ImageUtil.base64StringToByte(postBean.getAvatar()), null);
         map_portrait.setImageBitmap(bitmap);
         resultListener.onReturnResult(markerView);
    }

    //回调接口
    private interface ResultListener {
        void onReturnResult(View view);
    }

    //在地图上进行标记
    private void putDataToMarkerOptions(BitmapDescriptor pic, PostBean postBean) {
        LatLng point = new LatLng(postBean.getLatitude(), postBean.getLongitude());
        MarkerOptions overlayOptions = new MarkerOptions()
                .position(point)
                .icon(pic)
                .zIndex(15)
                .draggable(true)
                .animateType(MarkerOptions.MarkerAnimateType.grow);//设置marker从地上生长出来的动画

        Marker marker = (Marker) baiduMap.addOverlay(overlayOptions);
        Bundle bundle = new Bundle();
        bundle.putInt("promiseId", postBean.getPromiseId());
        marker.setExtraInfo(bundle);//marker点击事件监听时，可以获取到此时设置的数据
        marker.setToTop();
    }

    public class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation||bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                navigateTo(bdLocation); //基于网络定位，所以不能很精确的定位或者没有打开GPS进行定位
            }


        }
    }
}
  /* ①StringBuilder currentPosition=new StringBuilder();
            currentPosition.append("纬度：").append(bdLocation.getLatitude()).append("\n");
            currentPosition.append("经线：").append(bdLocation.getLongitude()).append("\n");
            currentPosition.append("国家：").append(bdLocation.getCountry()).append("\n");
            currentPosition.append("省：").append(bdLocation.getProvince()).append("\n");
            currentPosition.append("市：").append(bdLocation.getCity()).append("\n");
            currentPosition.append("区：").append(bdLocation.getDistrict()).append("\n");
            currentPosition.append("街道：").append(bdLocation.getStreet()).append("\n");

            currentPosition.append("定位方式：");
            if(bdLocation.getLocType()==BDLocation.TypeGpsLocation){
                currentPosition.append("GPS");
            }else if(bdLocation.getLocType()==BDLocation.TypeNetWorkLocation){
                currentPosition.append("网络");
            }
            positionText.setText(currentPosition);*/
/* ②坑爹货，不行，无论是四个参数的直接new一个InfoWindow.OnInfoWindowClickListener还是三个参数的回调onInfoWindowClick点击事件都不能响应自定义View里的点击事件
用getInfoView()，详情参上
    @Override
    public void onInfoWindowClick() {
        LinearLayout ll_info_detail = infoView.findViewById(R.id.ll_info_detail);
        ll_info_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), IndexActivity.class);
                startActivity(intent);

            }
        });
        infoView.findViewById(R.id.ll_info_phone).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }*/

  /*③定位的作用，默认是一个蓝色小圆点，可以通过MyLocationConfiguration设置自定义图标，注意！！是定位 不是Marker，点击它，响应的是onMyLocationClick 我的位置的点击事件
  MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, bitmapDescriptor);
        baiduMap.setMyLocationConfiguration(configuration); */  //设置蓝色小圆点的默认图标 ,也就是定位图标


/* MyLocationConfiguration configuration3 = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, bitmapDescriptor3);
        baiduMap.setMyLocationConfiguration(configuration3);*//* //如果不设置，就不显示图标了
        OverlayOptions overlayOptions3 = new MarkerOptions().position(point).icon(bitmapDescriptor3);//.animateType(MarkerOptions.MarkerAnimateType.grow)
        marker3 = (Marker)baiduMap.addOverlay(overlayOptions3);*/

         /* View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.map_view, null);
        CircleImageView map_portrait = view.findViewById(R.id.map_portrait);
        Glide.with(this).load(ImageUtil.base64StringToByte(avatar)).thumbnail(0.3f).into(map_portrait);*///加载不出来头像
        /* Glide.with(MyApplication.getContext()).asBitmap().load(bitmap).into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                map_portrait.setImageBitmap(resource);
                isPicOk = true;
            }
        });*/ //一直没有把获取的头像设置进map_portrait里去，但是Bitmap已经是头像了
       /* try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/     //也不是在bitmapDescriptor创建之前view还没加载好，而是要用setImageBitmap才能加载进图片，具体原因，改天分析


/*④ 不能添加多个marker
        × if(isLog()){
              overlayManager = new OverlayManager(baiduMap);
            List<OverlayOptions> overlayOptionsList = overlayManager.addToMap();

            List<Marker> mMarkerList = new ArrayList<Marker>();
             for (OverlayOptions option : overlayOptionsList) {
                 Marker marker = (Marker)baiduMap.addOverlay(option);
                 mMarkerList.add(marker);
            }
            baiduMap.addOverlays(overlayOptionsList);
            LatLng point = new LatLng(Utility.getPromise().getLongitude(), Utility.getPromise().getLatitude());
            MarkerOptions markerOptions = new MarkerOptions().position(point).icon(BitmapDescriptorFactory.fromResource(R.mipmap.map));
            Marker marker = (Marker)baiduMap.addOverlay(markerOptions);
            Bundle bundle = new Bundle();
            bundle.putInt("promiseId", 1);
             marker.setExtraInfo(bundle);
             }
      × baiduMap.clear();
        List<OverlayOptions> list = new ArrayList<>();
        MarkerOptions markerOptions = new MarkerOptions().position(new LatLng(location.getLatitude(),location.getLongitude())).icon(bitmapDescriptor);
        list.add(markerOptions);
        baiduMap.addOverlays(list);*/
        /*Marker marker = (Marker)baiduMap.addOverlay(markerOptions);
        Bundle bundle = new Bundle();
        bundle.putInt("promiseId", 1);
        marker.setExtraInfo(bundle);*/

/*⑤ 可见与不可见只需要将数据添加到数据库里就可以了，具体见notifyDataSetChanged
        LatLng point = new LatLng(Utility.getPromise().getLongitude(), Utility.getPromise().getLatitude());
       Bitmap bitmap = ImageUtil.getPictureFromBytes(ImageUtil.base64StringToByte(avatar), null);//把找到的头像变成Bitmap类型
        final View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.map_view, null);
        final CircleImageView map_portrait = view.findViewById(R.id.map_portrait);

        map_portrait.setImageBitmap(bitmap);//把Bitmap设置进CircleImageView里

            BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromView(view);

            Bundle bundle = new Bundle();
            bundle.putInt("promiseId", Utility.getPromise().getPromiseId());//通过参数判断点击的是哪个Marker
            OverlayOptions overlayOptions = new MarkerOptions().position(point).icon(bitmapDescriptor).zIndex(9).extraInfo(bundle);*///.animateType(MarkerOptions.MarkerAnimateType.grow)
//            marker2 = (Marker) baiduMap.addOverlay(overlayOptions); //addOverlay方法的返回值是Overlay，而Overlay恰好是一个抽象类，它的子类与OverlayOption的子类一一对应
//        MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, bitmapDescriptor);//不设置显示不出带有头像的Marker
//             baiduMap.setMyLocationConfiguration(configuration);    //定位图标


//            overlayOptionsList.add(overlayOptions);//overlayOptionsList当Fragment可见一次就增加一次
//            overlayManager.addToMap();

