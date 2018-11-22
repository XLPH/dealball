package com.example.dealball.main.plus;

import android.os.Bundle;

import com.example.dealball.main.bean.Promise;
import com.example.dealball.main.hello.Back;
import com.example.dealball.main.utils.Utility;

import org.litepal.LitePal;
import org.litepal.LitePalDB;

import java.util.Date;

class PlusPresenter implements PlusContact.Presenter {

    private PlusContact.View view;
    private PlusModel model;

    PlusPresenter(PlusContact.View view){
        this.view = view;
        model = PlusModel.getInstance();

    }




    @Override
    public void post(Integer allNumber, double longitude, double latitude, String time, Integer type, String context, boolean haveBall, String token) {
        model.post(allNumber, longitude, latitude, time, type, context, haveBall, token, new Back() {
            @Override
            public void success(Bundle bundle) {
                int code = bundle.getInt("code");
                Promise promise = bundle.getParcelable("promise");
                Utility.setPromise(promise);
                Utility.setPost(true);
                if(code == 0){
                    view.showToast("发布成功");
                    view.next();
                }

            }

            @Override
            public void failure(String meg) {

            }
        });
    }
}
