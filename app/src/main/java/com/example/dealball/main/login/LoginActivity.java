package com.example.dealball.main.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dealball.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    TextView register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        TextView tv_title=findViewById(R.id.tv_title);
        tv_title.setText("手机号登录");

        register = findViewById(R.id.register);

        register.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.register:
                Intent intent=new Intent(this,RegisterActivity.class);
                startActivity(intent);
                finish();
        }
    }
}
