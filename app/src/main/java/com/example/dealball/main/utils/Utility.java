package com.example.dealball.main.utils;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.dealball.main.bean.MyInfoBean;

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
        List<MyInfoBean> myInfoBeans = DataSupport.where("userId = ?", String.valueOf(userId) ).find(MyInfoBean.class);
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
}
