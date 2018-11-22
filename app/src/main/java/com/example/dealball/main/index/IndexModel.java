package com.example.dealball.main.index;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.dealball.main.HomeActivity;
import com.example.dealball.main.bean.Promise;
import com.example.dealball.main.hello.Back;
import com.example.dealball.main.utils.HttpUtil;
import com.example.dealball.main.utils.MyApplication;
import com.example.dealball.main.utils.URLStore;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

class IndexModel {
    private static IndexModel instance;
    private IndexModel() {}
    public static IndexModel getInstance() {
        if (instance == null) {
            synchronized (IndexModel.class) {
                if (instance == null) {
                    instance = new IndexModel();
                }
            }
        }
        return instance;
    }
    public void joinBall(String promiseId, String haveBall, String token, final Back back) {
        Map<String, String> params = new HashMap<>();
        params.put("promiseId", String.valueOf(promiseId));
        params.put("haveBall", haveBall);
        params.put("token", token);

        HttpUtil.post(URLStore.JOIN_POST, params, new Callback() {
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
