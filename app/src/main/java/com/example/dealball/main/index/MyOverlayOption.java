package com.example.dealball.main.index;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.bumptech.glide.Glide;
import com.example.dealball.R;
import com.example.dealball.main.bean.PostBean;
import com.example.dealball.main.utils.ImageUtil;
import com.example.dealball.main.utils.MyApplication;
import com.example.dealball.main.utils.Utility;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyOverlayOption {

    private BaiduMap mBaiduMap = null;
    private List<OverlayOptions> myOverlayOptionsList = null;

    MyOverlayOption(BaiduMap baiduMap) {
        mBaiduMap = baiduMap;
        if (myOverlayOptionsList == null) {
            myOverlayOptionsList = new ArrayList<OverlayOptions>();
        }
    }

    public List<OverlayOptions> getOverlayOptionsList(){

        List<PostBean> postBeanList = DataSupport.findAll(PostBean.class);
        int id = postBeanList.size();
//        List<OverlayOptions> overlayOptionsList = null;
        View view = LayoutInflater.from(MyApplication.getContext()).inflate(R.layout.map_view, null);
        CircleImageView map_portrait = view.findViewById(R.id.map_portrait);
       //不设置显示不出带有头像的Marker
        Bitmap bitmap = null;
        LatLng point = null;
        BitmapDescriptor bitmapDescriptor = null;
        for(int i = 0; i < postBeanList.size(); i++){
            bitmap = ImageUtil.getPictureFromBytes(ImageUtil.base64StringToByte(postBeanList.get(i).getAvatar()), null);
            map_portrait.setImageBitmap(bitmap);//把Bitmap设置进CircleImageView里
            Bitmap bitmapView = ImageUtil.getViewBitmap(view);
//            Glide.with(MyApplication.getContext()).load(postBeanList.get(i).getAvatar()).into(map_portrait);
            point = new LatLng(postBeanList.get(i).getLongitude(), postBeanList.get(i).getLatitude() );
//            bitmapDescriptor = BitmapDescriptorFactory.fromView(view);
            bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmapView);
//            MyLocationConfiguration configuration = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, bitmapDescriptor);
            Bundle bundle = new Bundle();
            bundle.putInt("promiseId", postBeanList.get(i).getPromiseId());
            OverlayOptions overlayOptions = new MarkerOptions().position(point).icon(bitmapDescriptor).zIndex(9).extraInfo(bundle);
            myOverlayOptionsList.add(overlayOptions);

        }
        return myOverlayOptionsList;
    }
}
