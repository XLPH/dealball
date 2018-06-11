package com.example.dealball.main.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.dealball.R;

public class RegisterActivity extends Activity implements View.OnClickListener {
    Button btn_go_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        TextView tv_title=findViewById(R.id.tv_title);
        tv_title.setText("注册");

        btn_go_login=findViewById(R.id.btn_go_login);

        btn_go_login.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go_login:
                Intent intent=new Intent(this,LoginBYActivity.class);
                startActivity(intent);
                finish();
        }
    }
}
