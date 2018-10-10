package com.example.dealball.main.utils;

import android.Manifest;
import android.support.annotation.RequiresPermission;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.Callback;
import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class HttpUtil {

   // private static final HttpUtil instance;
    private OkHttpClient client;
    private OkHttpClient client1;
    private static String JSESSIONID;



    private ConcurrentHashMap<String,List<Cookie>> cookieStore = new ConcurrentHashMap<>();

    private HttpUtil() {

        client = new OkHttpClient.Builder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(120, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .cookieJar(new CookieJar() {
                    @Override
                    public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {

                        cookieStore.put(url.host(),cookies);
                        System.out.println("cookies url:"+ url.toString());
                        for(Cookie cookie : cookies){
                            System.out.println("cookies:" + cookie.toString());
                            System.out.println("cookies name:"+ cookie.name());
                            System.out.println("cookies path:"+ cookie.path());
                            System.out.println("cookies value:"+ cookie.value());
                            JSESSIONID = cookie.value();

                        }



                    }

                    @Override
                    public List<Cookie> loadForRequest(HttpUrl url) {
                        List<Cookie> cookies = cookieStore.get(url.host());
                        return cookies != null ? cookies : new ArrayList<Cookie>();

                    }
                })
                .build();


    }
    private static class HttpUtilLoader{
        private static final HttpUtil instance=new HttpUtil();
    }
    public static HttpUtil getInstance(){
        return HttpUtilLoader.instance;
    }

    /*private  static HttpUtil getInstance() {
        if (instance == null) {
            synchronized (HttpUtil.class) {
                if (instance == null) {
                    instance = new HttpUtil();
                }
            }
        }
        return instance;
    }*/
    public static String getFailureMeg() {
        return "连接失败，请检查网络设置";
    }

    public static String getCodeMeg(int code) {
        switch (code) {
            case 0:return "请求成功";
            case -1:return "系统错误";
            case 1:return "参数错误";
            case 2:return "用户名不存在";
            case 3:return "密码错误";
            case 4:return "用户已存在";
            case 5:return "用户未登录";
            case 6:return "超时错误";
            case 7:return "已经验证过了";
            case 8:return "用户未验证";
            default:return "未知错误";
        }
    }




    @RequiresPermission(Manifest.permission.INTERNET)
    public static void post(String url, Map<String, String> params, Callback callback) {

        Request request = new Request.Builder()   //如果想发送一条HTTP请求，首先需要创建一个Request对象
                .url(url)                               //设置目标的网络地址
                .post(buildBody(params))
                .build();                       //可以在最终的build()方法之前连缀很多其他方法来丰富这个Request对象
        getInstance().client.newCall(request).enqueue(callback);
        //调用OKHTTPClient的newCall()方法来创建一个Call对象，并调用它的execute()方法来发送请求并获取服务器返回的数据（初级版本）
        // enqueue()方法的内部已经开了子线程，然后会在子线程中执行HTTP请求，并将最终的请求结果回调到Callback中，
        // 因为回调接口还是在子线程中运行的，所以不可以执行任何的UI操作，除非是借助runOnUIThread()方法来进行线程的转换
    }

    @RequiresPermission(Manifest.permission.INTERNET)
    public static void post1(String url, Map<String, String> params, Callback callback) {

            Request request = new Request.Builder()
                    .url(url)
                    .post(buildBody(params))
                    .addHeader("JESESSIONID",JSESSIONID)
                    .build();
            System.out.println("******"+request.header(JSESSIONID));
            //JESESSIONID

            getInstance().client.newCall(request).enqueue(callback);
        }

    @RequiresPermission(Manifest.permission.INTERNET)
    public static void myPost(String url, String str1, String str2, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody(str1, str2))
                .build();

        getInstance().client.newCall(request).enqueue(callback);
    }

    @RequiresPermission(Manifest.permission.INTERNET)
    public static void myPost(String url, Callback callback) {  //以get方式提交数据

        Request request = new Request.Builder()
                .url(url)
                //.addHeader("JESESSIONID",JSESSIONID)
                .build();

        getInstance().client.newCall(request).enqueue(callback);
    }



    @RequiresPermission(Manifest.permission.INTERNET)
    public static void post(String url, Map<String, String> params, Map<String, File> files, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .post(buildBody(params, files))
                .build();
        getInstance().client.newCall(request).enqueue(callback);
    }

    private static RequestBody buildBody(Map<String, String> params) {
        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.add(entry.getKey(), entry.getValue());
        }
        return builder.build();
    }

    private static RequestBody requestBody(String str1 , String str2){
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("phone",str1);
        builder.add("password",str2);
        return builder.build();

    }


    private static RequestBody buildBody(Map<String, String> params, Map<String, File> files) {
        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue());
        }
        for (Map.Entry<String, File> entry : files.entrySet()) {
            builder.addFormDataPart(entry.getKey(), entry.getValue().getName(),
                    RequestBody.create(MediaType.parse("application/octet-stream"), entry.getValue()));
        }
        return builder.build();
    }
}

