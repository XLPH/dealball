package com.example.dealball.main.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.dealball.main.bean.IsLogged;
import org.greenrobot.eventbus.EventBus;

import com.example.dealball.R;
import com.example.dealball.main.HomeActivity;
import com.example.dealball.main.utils.Utility;

public class RegisterActivity extends Activity implements View.OnClickListener ,HelloContact.View{
    private  Button btn_go_login;
    private EditText phone_number_et;
    private EditText password_et;
    private EditText confirm_password_et;
    private EditText auth_code_et;
    private TextView send_code_tv;
    private  Button btn_register;
    private Button btn_confirm;
    private ImageView iv_back;
    private RelativeLayout rl_loading;
    private HelloPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        TextView tv_title=findViewById(R.id.tv_title);
        tv_title.setText("注册");

        initView();
        initEvent();

    }
    private void initView() {
        phone_number_et = findViewById(R.id.phone_number_et);
        password_et = findViewById(R.id.password_et);
        confirm_password_et = findViewById(R.id.confirm_password_et);
        auth_code_et = findViewById(R.id.auth_code_et);
        send_code_tv = findViewById(R.id.send_code_tv);
        btn_register = findViewById(R.id.btn_register);
        btn_go_login = findViewById(R.id.btn_go_login);
        btn_confirm=findViewById(R.id.btn_confirm);
        iv_back = findViewById(R.id.iv_back);
        rl_loading = findViewById(R.id.rl_loading);
        presenter = new HelloPresenter(this);
    }
    private void initEvent() {
        btn_register.setOnClickListener(this);
        btn_go_login.setOnClickListener(this);
        send_code_tv.setOnClickListener(this);
        btn_confirm.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case  R.id.send_code_tv:
                String phone=phone_number_et.getText().toString().trim();

                if(phone.length()!=11){
                    Toast.makeText(this,"无效的手机号",Toast.LENGTH_LONG).show();
                    return;
                }
                new MyCountDownTimer(60 * 1000, 1000).start();
                presenter.sendCode(phone);

                break;
            case R.id.btn_register:
                phone=phone_number_et.getText().toString().trim();
                String password=password_et.getText().toString().trim();
                String psw2=confirm_password_et.getText().toString().trim();
                String param=auth_code_et.getText().toString().trim();
                presenter.confirmCode(phone, password,psw2,param);
               // presenter.register(phone, password,psw2,param);

                break;
            case R.id.btn_confirm:
                /*param=auth_code_et.getText().toString().trim();
                presenter.confirmCode(param);*/
                break;
            case R.id.btn_go_login:
                Intent intent=new Intent(this,LoginBYActivity.class);
                startActivity(intent);
                finish();
            case R.id.iv_back:
                finish();
        }
    }

    @Override
    public void next() {
        EventBus.getDefault().postSticky(IsLogged.MARK);
        Intent intent= new Intent(this,HomeActivity.class);
        intent.putExtra("page",4);
        startActivity(intent);
        finish();

    }

    @Override
    public void showProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rl_loading.setVisibility(View.VISIBLE);
            }
        });

    }

    @Override
    public void hideProgress() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                rl_loading.setVisibility(View.GONE);
            }
        });

    }

    @Override
    public void showToast(final String meg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Utility.makeToast(meg,Toast.LENGTH_SHORT);
            }
        });

    }
    private class MyCountDownTimer extends CountDownTimer {

        public MyCountDownTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long l) {
            send_code_tv.setClickable(false);
            send_code_tv.setTextColor(getResources().getColor(R.color.unable));
            send_code_tv.setText(l / 1000 + "s后重新发送");
        }

        @Override
        public void onFinish() {
            send_code_tv.setTextColor(getResources().getColor(R.color.colorAccent));
            send_code_tv.setText("发送验证码");
            send_code_tv.setClickable(true);
        }
    }
}
