package com.example.dealball.main.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dealball.R;

public class LoginBYActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView phone_tv;
    private TextView we_chat_tv;
    private TextView tencent_qq_tv;
    private ImageView iv_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_by);

        TextView tv_title=findViewById(R.id.tv_title);
        tv_title.setText("登录");

        initView();
        initEvent();


    }
    public void initView(){
        phone_tv = findViewById(R.id.phone_tv);
        we_chat_tv = findViewById(R.id.we_chat_tv);
        tencent_qq_tv = findViewById(R.id.tencent_qq_tv);
        iv_back = findViewById(R.id.iv_back);
    }
    public void initEvent(){
        phone_tv.setOnClickListener(this);
        we_chat_tv.setOnClickListener(this);
        tencent_qq_tv.setOnClickListener(this);
        iv_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.phone_tv:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
            case R.id.iv_back:
                finish();
            case R.id.we_chat_tv:
                break;
            case R.id.tencent_qq_tv:
                break;

        }
    }
}
