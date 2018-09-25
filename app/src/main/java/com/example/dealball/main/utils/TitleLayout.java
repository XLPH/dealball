package com.example.dealball.main.utils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dealball.R;


public class TitleLayout extends LinearLayout {

    private TextView tv_right;
    private ImageView common_back;

    public TitleLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.title_settings,this);

        initView();

        initListener();
    }

    private void initListener() {
        common_back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                //结束当前活动
                ((Activity)getContext()).finish();
            }
        });
    }

    private void initView() {
        tv_right = findViewById(R.id.register);
        common_back = findViewById(R.id.arrow);
    }
}
