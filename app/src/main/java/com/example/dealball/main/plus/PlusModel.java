package com.example.dealball.main.plus;

import android.os.Bundle;

import com.example.dealball.main.bean.Promise;
import com.example.dealball.main.hello.Back;
import com.example.dealball.main.utils.HttpUtil;
import com.example.dealball.main.utils.URLStore;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

class PlusModel {
    private static PlusModel instance;
    private PlusModel() {}
    public static PlusModel getInstance() {
        if (instance == null) {
            synchronized (PlusModel.class) {
                if (instance == null) {
                    instance = new PlusModel();
                }
            }
        }
        return instance;
    }

    public void post(Integer allNumber, double longitude, double latitude, String time, Integer type, String context, boolean haveBall, String token, final Back back) {
        Map<String, String> params = new HashMap<>();
        params.put("allNumber", String.valueOf(allNumber));
        params.put("longitude", String.valueOf(longitude));
        params.put("latitude",String.valueOf(latitude));
        params.put("time", time);
        params.put("type", String.valueOf(type));
        params.put("context",context);
        params.put("haveBall" , String.valueOf(haveBall));
        params.put("token", token);

        HttpUtil.post(URLStore.PUBLISH_POST, params, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println(HttpUtil.getFailureMeg());
                back.failure(HttpUtil.getFailureMeg());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                System.out.println(responseData);
                try {
                    JSONObject object = new JSONObject(responseData);
                    int code = object.optInt("code");
                    String promise = object.optString("promise");
                    Gson gson = new Gson();
                    Promise promise1 = gson.fromJson(promise, Promise.class);
                    Bundle bundle = new Bundle();
                    bundle.putParcelable("promise", promise1);
                    bundle.putInt("code", code);
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
