package com.example.dealball.main.hello;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;

import com.example.dealball.main.utils.Utility;

public class HelloPresenter implements HelloContact.Presenter{
    private HelloContact.View view;
    private HelloModel model;


    public HelloPresenter(HelloContact.View view){
        this.view=view;
        model=HelloModel.getInstance();
    }
    @Override
    public void getUser(int id){


    }




    @Override
    public void login(String phone, String password) {
        view.showProgress();
        model.login(phone, password, new Back() {
            @Override
            public void success(@Nullable Bundle bundle) {

                view.showToast("登录成功");
               // int code=bundle.getInt("code");

                int id=bundle.getInt("id");     //获取登录传过来的用户id，唯一标识用户

               /* if(code==0){        //如果登录成功，把id存储起来

                }*/
               if(id != -1){
                   System.out.print("登录成功返回了llllllllllllllllllllllllllll");
                   //Utility.setUserById(id);
                   view.next();
               }


            }

            @Override
            public void failure(String meg) {
                updateFailureView(meg);
            }
        });

    }

    @Override
    public void sendCode(String phone) {
        model.sendCode(phone, new Back() {

            @Override
            public void  success(@Nullable Bundle bundle) {
                //view.showToast("发送验证码成功,返回码是"+bundle.getInt("code"));

            }

            @Override
            public void failure(String meg) {
                updateFailureView(meg);
            }
        });

    }

    @Override
    public void confirmCode(final String phone, final String password, final String psw2, final String param) {
        if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)|| TextUtils.isEmpty(psw2)
                || phone.length() != 11) {
            view.showToast("请填写完整信息");
            return;
        }
        if (!password.equals(psw2)) {
            view.showToast("两次输入密码不同");
            return;
        }
        if( TextUtils.isEmpty(param)){
            view.showToast("验证码不能为空");
            return;
        }

        model.confirmCode(param, new Back() {
            @Override
            public void success(Bundle bundle) {
                int code=bundle.getInt("code");
                if(code==0){
                    register(phone,password,psw2,param);
                }


            }
            @Override
            public void failure(String meg) {
                updateFailureView(meg);
            }
        });

    }

    @Override
    public void register(final String phone, final String password, String psw2, String param) {

        model.register(phone, password,new Back() {
            @Override
            public void success( Bundle bundle) {

                System.out.print("注册了lllllllllllllllllllllllllllllll");
                view.showToast("注册成功");

                int userId = bundle.getInt("userId");
                if(userId != -1){
                    login(phone, password);
                }

              /*  int code=bundle.getInt("code");
                if(code==0){
                    login(phone,password);
                }*/

            }

            @Override
            public void failure(String meg) {
                updateFailureView(meg);
            }
        });

    }
    private void updateFailureView(String meg) {


    }
}