package com.example.dealball.main.utils;

import android.app.Application;
import android.content.Context;

public class MyApplication extends Application { //     定制自己的Application类，管理程序内一些全局的状态信息，比如说获取Context对象

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }



    public static Context getContext() {
        return context;
    }

}
