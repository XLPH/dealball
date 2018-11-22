package com.example.dealball.main.mine.info;

import android.os.Bundle;

import com.example.dealball.main.bean.MyInfoBean;
import com.example.dealball.main.hello.Back;
import com.example.dealball.main.utils.ImageUtil;
import com.example.dealball.main.utils.Utility;

public class MyInfoPresenter implements MyInfoContact.Presenter {

    private MyInfoContact.View view;
    private MyInfoModel model;

    MyInfoPresenter(MyInfoContact.View view){
        this.view = view;
        model = MyInfoModel.getInstance();

    }

    @Override
    public void updateInfo(final String updateName, final String msg, String token) {
        model.updateInfo(updateName, msg, token, new Back() {
            @Override
            public void success(Bundle bundle) {
                int code = bundle.getInt("code");
                MyInfoBean myInfoBean = bundle.getParcelable("myInfoBean");
                Utility.setMyInfoBean(myInfoBean);
                if(code == 0){
                    view.updateMessage(updateName, msg);
                }
                view.showToast("更新成功");

            }

            @Override
            public void failure(String meg) {

            }
        });

    }

    public void getBytes(){
        model.getByte(new Back() {
            @Override
            public void success(Bundle bundle) {

            }

            @Override
            public void failure(String meg) {

            }
        });
    }

    public void updateInfo(final String updateName, final byte[] msg, String token) {
        model.updateInfo(updateName, msg, token, new Back() {
            @Override
            public void success(Bundle bundle) {
                int code = bundle.getInt("code");
                MyInfoBean myInfoBean = bundle.getParcelable("myInfoBean");
                Utility.setMyInfoBean(myInfoBean);
                if(code == 0){
                    view.updateMessage(updateName, ImageUtil.byteToBase64String(msg));
                }
                view.showToast("更新成功");
            }

            @Override
            public void failure(String meg) {

            }
        });
    }

}
