package com.example.dealball.main.hello;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.LinearLayout;
import com.example.dealball.R;

public class LoginBYActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView by_back;
    private LinearLayout ll_phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by);

        TextView tv_title=findViewById(R.id.tv_title);
        tv_title.setText("登录");

        initView();
        initData();
        initEvent();


    }
    public void initView(){
       ll_phone = findViewById(R.id.ll_phone);
        by_back = findViewById(R.id.by_back);
    }

    public void initData(){
        by_back.setVisibility(View.VISIBLE);

    }

    public void initEvent(){
        ll_phone.setOnClickListener(this);
        by_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.ll_phone:
                Intent intent = new Intent(this,LoginActivity.class);
                startActivity(intent);
                finish();
            case R.id.by_back:
                finish();
                break;


        }
    }
}
