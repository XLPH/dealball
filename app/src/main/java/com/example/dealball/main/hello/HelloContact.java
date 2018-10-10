package com.example.dealball.main.hello;

import com.example.dealball.main.base.BaseView;

public interface HelloContact {
    interface View extends BaseView{
        void next();
        void showProgress();
        void hideProgress();
    }

    interface Presenter {
        void login(String phone, String password);
        void sendCode(String phone);
        void confirmCode(String phone, String password, String psw2,String param);
        void register(String phone, String password, String psw2,String param);
        void getUser(int id, String token);
    }
}
