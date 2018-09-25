package com.example.dealball.main.hello;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.dealball.main.bean.UserBean;
import com.example.dealball.main.utils.HttpUtil;
import com.example.dealball.main.utils.ParseMD5;
import com.example.dealball.main.utils.URLStore;
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

    public void getUser(int id,final Back back){
        Map<String,String> params = new HashMap<>();
        params.put("id",id+"");
        HttpUtil.post(URLStore.USERINFO_GET, params, new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    JSONObject object = new JSONObject(response.body().string());
                    System.out.println(response.code());
                    // String sessionId = object.optString("JESESSIONID");
                    System.out.println(response.header("JESESSIONID"));

                    System.out.println();
                    // System.out.println("sessionId:"+sessionId);
                    Log.d("send", object.toString());
                    int id = object.optInt("id");
                    int code1 = object.optInt("code");

                    System.out.println(id);
                    System.out.println(code1);
                    final int status = object.getInt("code");
                    if (status != 0) {
                        back.failure(HttpUtil.getCodeMeg(status));
                        System.out.println("HttpUtil.getCodeMeg(status"+HttpUtil.getCodeMeg(status));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code",object.optInt("code"));
                        back.success(bundle);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
               // try {
                   // JSONObject object = new JSONObject(response.body().string());


                    System.out.println("成功登录了lllllllllllllllllll");
                  //  Log.d("login", object.toString());
                  //  int id = object.optInt("id");
                    int id = Integer.parseInt(response.body().string());
                    System.out.println(id);
                    Bundle bundle = new Bundle();
                    bundle.putInt("id",id);
                    back.success(bundle);
                   /* int code1 = object.optInt("code");
                    System.out.println(id);
                    System.out.println(code1);
                    final int status = object.getInt("code");
                    if (status != 0) {
                        back.failure(HttpUtil.getCodeMeg(status));
                        System.out.println("HttpUtil.getCodeMeg(status"+HttpUtil.getCodeMeg(status));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code",object.optInt("code"));
                        bundle.putInt("id",object.optInt("id"));
                        back.success(bundle);
                    }*/
               /* } catch (JSONException e) {
                    e.printStackTrace();
                })*/
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
                System.out.println("失败了llllllllllllllllllllllllllllllllll");
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {

                System.out.println("响应了llllllllllllllllllllllllllllllllll");
                // try {

                String responseData = response.body().string();
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(responseData, UserBean.class);
                int id = userBean.getId();
                String phone = userBean.getPhone();
                String password = userBean.getPassword();
                String salt = userBean.getSalt();
                System.out.println("id:" + id);
                System.out.println("phone:" + phone);
                System.out.println("pass:" + password);
                System.out.println("salt:" + salt);
                System.out.print(userBean.toString());
                Bundle bundle = new Bundle();
                bundle.putInt("userId",id);
                back.success(bundle);

                   /* JSONObject object = new JSONObject(response.body().string());

                    int id = object.optInt("id");
                    int code1 = object.optInt("code");
                    Log.d("register", object.toString());
                    System.out.println(id);
                    System.out.println(code1);
                    final int status = object.getInt("code");
                    if (status == 0) {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code",object.optInt("code"));
                        System.out.println("code_code"+status);
                        back.success(bundle);

                    } else {
                        //back.success(null);
                        back.failure(HttpUtil.getCodeMeg(status));
                        System.out.println("HttpUtil.getCodeMeg(status"+HttpUtil.getCodeMeg(status));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });*/
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
                    System.out.println(response.code());
                   // String sessionId = object.optString("JESESSIONID");
                    System.out.println(response.header("JESESSIONID"));

                    System.out.println();
                   // System.out.println("sessionId:"+sessionId);
                    Log.d("send", object.toString());
                    int id = object.optInt("id");
                    int code1 = object.optInt("code");

                    System.out.println(id);
                    System.out.println(code1);
                    final int status = object.getInt("code");
                    if (status != 0) {
                        back.failure(HttpUtil.getCodeMeg(status));
                        System.out.println("HttpUtil.getCodeMeg(status"+HttpUtil.getCodeMeg(status));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code",object.optInt("code"));
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
        HttpUtil.post1(URLStore.USER_CODE, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
               try {
                    JSONObject object = new JSONObject(response.body().string());
                    Log.d("confirm", object.toString());
                    int id = object.optInt("id");
                    int code1 = object.optInt("code");
                    System.out.println(id);
                    System.out.println(code1);
                    final int status = object.optInt("code");
                    if (status != 0) {
                        back.failure(HttpUtil.getCodeMeg(status));
                        System.out.println("HttpUtil.getCodeMeg(status"+HttpUtil.getCodeMeg(status));
                    } else {
                        Bundle bundle = new Bundle();
                        bundle.putInt("code",object.optInt("code"));
                        back.success(bundle);

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
