package com.example.dealball.main.utils;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.IntDef;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

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
        int id=sp.getInt("id", -1);
        System.out.println("id"+id);
        return id;

    }
}
