package com.example.dealball.main.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.dealball.main.bean.MapBean;
import com.example.dealball.main.bean.MyInfoBean;
import com.example.dealball.main.bean.Promise;

import org.litepal.crud.DataSupport;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.List;

public class Utility {

    private static Toast toast;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({Toast.LENGTH_SHORT, Toast.LENGTH_LONG})
    private @interface NavigationMode {}

    public static void makeToast(String meg, @NavigationMode int l) {
        if (toast == null) {
            toast = Toast.makeText(MyApplication.getContext(), meg, l);
        } else {
            toast.setText(meg);
        }
        toast.show();
    }

    public synchronized static void setUserById(int id) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE).edit();
        editor.putInt("id",id);
        editor.apply();
    }

    @Nullable
    public synchronized static int getUserById() {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        int id=sp.getInt("id", 0);
        System.out.println("id"+id);
        return id;

    }
    public synchronized static void setMyInfoBean(MyInfoBean myInfoBean) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("myInfo", Context.MODE_PRIVATE).edit();
        editor.putInt("userId", myInfoBean.getUserId());
        editor.apply();
        myInfoBean.saveOrUpdate(myInfoBean.getUserId());
    }

    @Nullable
    public synchronized static MyInfoBean getMyInfoBean() {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("myInfo", Context.MODE_PRIVATE);
        int userId = sp.getInt("userId",0);
        List<MyInfoBean> myInfoBeans = DataSupport.where("userId = ?", String.valueOf(userId) ).find(MyInfoBean.class);//返回的是所有满足条件的集合，点进去看详情
        return myInfoBeans.get(0);

    }

    public synchronized static void setToken(String token) {
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("isLogged", Context.MODE_PRIVATE).edit();
        editor.putString("token", token);
        editor.apply();
    }

    @Nullable
    public synchronized static String getToken() {
        SharedPreferences pref = MyApplication.getContext().getSharedPreferences("isLogged", Context.MODE_PRIVATE);
        return pref.getString("token", "");
    }

    public synchronized static void setPost(Boolean isPost){
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("isPost", Context.MODE_PRIVATE).edit();
        editor.putBoolean("isPost", isPost);
        editor.apply();
    }

    public synchronized static Boolean getPost(){
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("isPost", Context.MODE_PRIVATE);
        return sp.getBoolean("isPost", false);
    }

    public synchronized static void setPromise(Promise promise){
        SharedPreferences.Editor editor = MyApplication.getContext().getSharedPreferences("myPromise", Context.MODE_PRIVATE).edit();
        editor.putInt("promiseId", promise.getPromiseId());
        /*editor.putLong("longitude", promise.getLongitude().longValue());//在虚机上转换后经度纬度为0是因为E-次方
        editor.putLong("latitude", promise.getLatitude().longValue());*/
        editor.apply();
        promise.saveOrUpdate(promise.getUserId());
    }
    @Nullable
    public synchronized static Promise getPromise() {
        SharedPreferences sp = MyApplication.getContext().getSharedPreferences("myPromise", Context.MODE_PRIVATE);
        int promiseId = sp.getInt("promiseId",0);
        /*long longitude = sp.getLong("longitude",0);
        long latitude = sp.getLong("latitude",0);
        MapBean mapBean = new MapBean(longitude, latitude);*/ //没有创建约定打球的表！而且传过去的经纬度变为long会把小数点省掉，导致定位有偏差
//        String avatar = DataSupport.select("avatar").where("userId = ?",String.valueOf(userId)).find(MyInfoBean.class).get(0).getAvatar();
//        ImageUtil.getFileFromBytes(ImageUtil.base64StringToByte(avatar),"avatarFile");
        List<Promise> promise = DataSupport.where("promiseId = ?", String.valueOf(promiseId) ).find(Promise.class); //数据库的类要继承DataSupport，还要配置数据库xml文件
        //根据自己的promiseId来返回自己发帖的所有信息，应该Sharesprefence只能put几个基本数据类型，不能保存所有的信息
        return promise.get(0);  //还是要返回获取到的Promise对象，点击Marker跳转的时候要显示约球的信息
        //为什么有时候登录直接跳转到地图可以显示头像，有时就报错呢？因为第一次发表的发帖被保存了，没有清空，所以第二次来还能返回数据，但是如果是第一次的话，就是空的。
    }

}
