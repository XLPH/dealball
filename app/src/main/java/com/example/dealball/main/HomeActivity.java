package com.example.dealball.main;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.chaychan.library.BottomBarLayout;
import com.example.dealball.R;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = findViewById(R.id.view_pager);
        BottomBarLayout bottomBarLayout = findViewById(R.id.bottom_bar_layout);

        viewPager.setAdapter(new HomeAdapter(getSupportFragmentManager()));
        bottomBarLayout.setViewPager(viewPager);
    }
}
