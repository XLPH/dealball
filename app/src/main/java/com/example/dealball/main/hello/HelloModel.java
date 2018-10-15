package com.example.dealball.main.hello;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.dealball.main.bean.MyInfoBean;
import com.example.dealball.main.bean.UserBean;
import com.example.dealball.main.utils.HttpUtil;
import com.example.dealball.main.utils.ParseMD5;
import com.example.dealball.main.utils.URLStore;
import com.example.dealball.main.utils.Utility;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HelloModel {
    private static HelloModel instance;
    private HelloModel() {}
    public static HelloModel getInstance() {
        if (instance == null) {
            synchronized (HelloModel.class) {
                if (instance == null) {
                    instance = new HelloModel();
                }
            }
        }
        return instance;
    }

    public void getUser(int id, String token, final Back back){
        String showId = String.valueOf(id);
        Map<String, String> params = new HashMap<>();
        params.put("showId",showId);
        params.put("token", token);
        HttpUtil.post(URLStore.USERINFO_GET, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Gson gson = new Gson();
                MyInfoBean myInfoBean = gson.fromJson(responseData, MyInfoBean.class);
                System.out.println(myInfoBean.toString());
                int id = myInfoBean.getUserId();
                System.out.println("id:"+id);
                Bundle bundle = new Bundle();
                bundle.putInt("id",id);
                bundle.putParcelable("myInfoBean",myInfoBean);
                back.success(bundle);

            }
        });
    }
    public void login(String phone, String password, final Back back){
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", ParseMD5.parseStrToMd5U16(password));
        HttpUtil.post(URLStore.USER_LOGIN, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    Log.d("login", object.toString());
                    int id = object.optInt("id");
                    int code = object.optInt("code");
                    String token = object.optString("token");
                    System.out.println("响应登录返回id："+id);
                    System.out.println("响应登录返回码code："+code);
                    if (code != 0) {
                        back.failure(HttpUtil.getCodeMeg(code));
                        System.out.println("HttpUtil.getCodeMeg(status" + HttpUtil.getCodeMeg(code));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code", object.optInt("code"));
                        bundle.putInt("id", object.optInt("id"));
                        bundle.putString("token",token);
                        back.success(bundle);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void register(String phone, String password, final Back back) {
      /*  Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", ParseMD5.parseStrToMd5U16(password));*/
        // params.put("param", param);
        String passwordMd5 = ParseMD5.parseStrToMd5U16(password);
        HttpUtil.myPost(URLStore.USER_REGISTER, phone, passwordMd5, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                try {
                /* String responseData = response.body().string();
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(responseData, UserBean.class);
                int id = userBean.getId();
                String phone = userBean.getPhone();
                String password = userBean.getPassword();
                String salt = userBean.getSalt();
                System.out.print(userBean.toString());
                Bundle bundle = new Bundle();
                bundle.putInt("userId",id);
                back.success(bundle);*/

                    JSONObject object = new JSONObject(response.body().string());

                    int id = object.optInt("id");
                    int code = object.optInt("code");
                    Log.d("register", object.toString());
                    System.out.println("注册成功返回id："+id);
                    System.out.println("注册成功返回码code："+code);
                    if (code == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code", code);
                        System.out.println("code_code" + code);
                        back.success(bundle);

                    } else {
                        back.failure(HttpUtil.getCodeMeg(code));
                        System.out.println("HttpUtil.getCodeMeg(status" + HttpUtil.getCodeMeg(code));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void sendCode(String phone, final Back back) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        HttpUtil.post(URLStore.USER_SEND, params, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    String sessionId = object.optString("JESESSIONID");
                    System.out.println("sessionId:"+sessionId);
                   /* System.out.println(response.code());JSESSIONID
                    System.out.println(response.header("JESESSIONID"));
                    System.out.println();
                     int id = object.optInt("id");
                    System.out.println(id);*/
                    Log.d("send", object.toString());
                    System.out.print(object.toString());
                    int code = object.optInt("code");
                    System.out.println("发送返回码code:"+code);
                    if (code != 0) {
                        back.failure(HttpUtil.getCodeMeg(code));
                        System.out.println("HttpUtil.getCodeMeg(status"+HttpUtil.getCodeMeg(code));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code",code);
                        //bundle.putString("token",sessionId);
                        back.success(bundle);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public void confirmCode(String param, final Back back) {
        Map<String, String> params = new HashMap<>();
        params.put("param", param);
       // HttpUtil.post1(URLStore.USER_CODE, params, new Callback() 没有cookie
        HttpUtil.post1(URLStore.USER_CODE, params, new Callback(){
            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               try {
                    JSONObject object = new JSONObject(response.body().string());
                   // Log.d("confirm", object.toString());
                    System.out.print("confirm:"+object.toString());
                    //int id = object.optInt("id");
                    int code = object.optInt("code");
                    //System.out.println(id);
                    System.out.println("验证返回码code："+code);
                    if (code != 0) {
                        back.failure(HttpUtil.getCodeMeg(code));
                        System.out.println("HttpUtil.getCodeMeg(status"+HttpUtil.getCodeMeg(code));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code",code);
                        back.success(bundle);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
