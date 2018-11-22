package com.example.dealball.main.mine.settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dealball.R;
import com.example.dealball.main.bean.IsLogged;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.tv_title)
    TextView tv_title;
    @BindView(R.id.iv_back)
    ImageView iv_back;
    @BindView(R.id.bt_exit)
    Button bt_exit;
    @BindView(R.id.rl_exit)
    RelativeLayout rl_exit;
    @BindView(R.id.ll_setting_account)
    LinearLayout llSettingAccount;
    @BindView(R.id.ll_setting_check_update)
    LinearLayout llSettingCheckUpdate;
    @BindView(R.id.ll_my_more)
    LinearLayout llMyMore;
    @BindView(R.id.ll_my_about)
    LinearLayout llMyAbout;
    @BindView(R.id.ll_my_help)
    LinearLayout llMyHelp;
    @BindView(R.id.activity_setting)
    LinearLayout activitySetting;

    @OnClick(R.id.iv_back)
    public void onBackClick(){
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);

        initData();
        initEvent();

    }

    private void initEvent() {
        bt_exit.setOnClickListener(this);
    }

    private void initData() {
        tv_title.setText("设置");
    }

    @Override
    public void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().removeAllStickyEvents();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void IsLoggedBus(IsLogged isLogged) {
        if (isLogged.getName() != null) {
            System.out.println(isLogged.getName());
            rl_exit.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_exit:
                finish();
            /*case R.id.iv_back:
                finish();*/
        }
    }
}
