package com.example.dealball.main;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.chaychan.library.BottomBarLayout;
import com.example.dealball.R;
import com.example.dealball.main.bean.IsLogged;
import com.example.dealball.main.utils.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.view_pager);
        BottomBarLayout bottomBarLayout = findViewById(R.id.bottom_bar_layout);

        viewPager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
        bottomBarLayout.setViewPager(viewPager);

        Intent intent = getIntent();
        int page=intent.getIntExtra("page",0);
        viewPager.setCurrentItem(page);
    }

//    @Override
//    public void onResume(){
//        super.onResume();
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onDestroy(){
//        super.onDestroy();
//        EventBus.getDefault().unregister(this);
//    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void IsLoggedBus(IsLogged isLogged){
//
//    }
}
