package com.example.dealball.main.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dealball.R;

public class LoginBYActivity extends AppCompatActivity implements View.OnClickListener {
    TextView phone_tv;
    TextView we_chat_tv;
    TextView tencent_qq_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_by);

        phone_tv = findViewById(R.id.phone_tv);
        we_chat_tv = findViewById(R.id.we_chat_tv);
        tencent_qq_tv = findViewById(R.id.tencent_qq_tv);

        TextView tv_title=findViewById(R.id.tv_title);
        tv_title.setText("登录");

        phone_tv.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.phone_tv:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
            case R.id.we_chat_tv:
                break;
            case R.id.tencent_qq_tv:
                break;

        }
    }
}
