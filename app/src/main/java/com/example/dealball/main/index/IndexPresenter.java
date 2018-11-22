package com.example.dealball.main.index;

import android.os.Bundle;

import com.example.dealball.main.HomeActivity;
import com.example.dealball.main.hello.Back;

class IndexPresenter implements IndexContact.Presenter {
    private IndexContact.View view;
    private IndexModel model;

    IndexPresenter(IndexContact.View view){
        this.view = view;
        model = IndexModel.getInstance();
    }

    @Override
    public void joinBall(String promiseId, String haveBall, String token) {
        model.joinBall(promiseId, haveBall, token, new Back() {
            @Override
            public void success(Bundle bundle) {
                view.showToast("预约成功");
                view.next();
            }

            @Override
            public void failure(String meg) {
                view.showToast("预约失败");
            }
        });
    }

}
