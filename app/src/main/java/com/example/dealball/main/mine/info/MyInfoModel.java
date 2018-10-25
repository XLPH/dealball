package com.example.dealball.main.mine.info;

import android.os.Bundle;

import com.example.dealball.main.bean.MyInfoBean;
import com.example.dealball.main.hello.Back;
import com.example.dealball.main.utils.HttpUtil;
import com.example.dealball.main.utils.ImageUtil;
import com.example.dealball.main.utils.URLStore;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MyInfoModel {
    private static MyInfoModel instance;
    private MyInfoModel() {}
    public static MyInfoModel getInstance() {
        if (instance == null) {
            synchronized (MyInfoModel.class) {
                if (instance == null) {
                    instance = new MyInfoModel();
                }
            }
        }
        return instance;
    }

    public void updateInfo(String updateName, String msg, String token, final Back back){
        Map<String, String> params = new HashMap<>();
        params.put("updateName",updateName);
        params.put(updateName,msg);
        params.put("token",token);

        HttpUtil.post(URLStore.USERINFO_UPDATE, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    JSONObject object = new JSONObject(responseData);
                    int code = object.optInt("code");
                    String information = object.optString("information");
                    Gson gson = new Gson();
                    MyInfoBean myInfoBean = gson.fromJson(information, MyInfoBean.class);
                    System.out.println(myInfoBean.toString());
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("myInfoBean",myInfoBean);
                    bundle.putInt("code",code);
                    if(code == 0){
                        back.success(bundle);
                    }else{
                        back.failure(HttpUtil.getCodeMeg(code));
                        System.out.println("HttpUtil.getCodeMeg(status" + HttpUtil.getCodeMeg(code));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public void getByte(final Back back){

        HttpUtil.myPost(URLStore.GET_BYTES, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                JSONObject object = null;
                try {
                    object = new JSONObject(responseData);
                    String bytes = object.optString("bytes");
                    System.out.println(bytes);
                    byte[] b = responseData.getBytes();
                    System.out.println(b);
                    String s = new String(b);
                    System.out.println(s);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    public void updateInfo(String updateName, byte[] msg, String token, final Back back){

        String s = ImageUtil.byteToBase64String(msg);
       /* String s1 = null;
        try {
            s1 = new String(msg, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }*/
        Map<String, String> params = new HashMap<>();
        params.put("updateName",updateName);
        params.put(updateName,s);
        params.put("token",token);
        HttpUtil.post(URLStore.USERINFO_UPDATE, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                try {
                    JSONObject object = new JSONObject(responseData);
                    int code = object.optInt("code");
                    String information = object.optString("information");
                    System.out.println(responseData);
                    Gson gson = new Gson();
                    MyInfoBean myInfoBean = gson.fromJson(information, MyInfoBean.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("myInfoBean",myInfoBean);
                    bundle.putInt("code",code);
                    if(code == 0){
                        back.success(bundle);
                    }else{
                        back.failure(HttpUtil.getCodeMeg(code));
                        System.out.println("HttpUtil.getCodeMeg(status" + HttpUtil.getCodeMeg(code));
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
