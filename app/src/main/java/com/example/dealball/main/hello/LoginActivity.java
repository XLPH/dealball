package com.example.dealball.main.hello;

import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dealball.R;
import com.example.dealball.main.HomeActivity;
import com.example.dealball.main.utils.Utility;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener , HelloContact.View{
    private TextView register;
    private EditText phone_number_et;
    private EditText password_et;
    private Button login;
    private ImageView arrow;
    private RelativeLayout rl_loading;
    private HelloContact.Presenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView tv_title=findViewById(R.id.tv_title);
        tv_title.setText("手机号登录");

        initView();
        initEvent();


    }
    private void initView() {
        register = findViewById(R.id.register);
        phone_number_et = findViewById(R.id.phone_number_et);
        password_et = findViewById(R.id.password_et);
        login = findViewById(R.id.login);
        arrow = findViewById(R.id.arrow);
        rl_loading = findViewById(R.id.rl_loading);
        presenter = new HelloPresenter(this);
    }
    private void initEvent() {
        register.setOnClickListener(this);
        login.setOnClickListener(this);
        arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login:
                String phone = phone_number_et.getText().toString();
                String password = password_et.getText().toString();
                if (TextUtils.isEmpty(phone) || TextUtils.isEmpty(password)) {
                    showToast("用户名或密码不能为空");
                    return;
                }
                presenter.login(phone, password);
                break;
            case R.id.register:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                finish();
            case R.id.arrow:
                finish();
        }
    }

    @Override
    public void next() {
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
                Looper.prepare();
                Toast.makeText(getApplicationContext(), meg, Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        });

    }
}
