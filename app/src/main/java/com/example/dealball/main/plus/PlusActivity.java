package com.example.dealball.main.plus;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.example.dealball.R;
import com.example.dealball.main.HomeActivity;
import com.example.dealball.main.bean.IsLogged;
import com.example.dealball.main.bean.IsPromise;
import com.example.dealball.main.index.IndexFragment;
import com.example.dealball.main.utils.Utility;

import org.greenrobot.eventbus.EventBus;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PlusActivity extends AppCompatActivity implements View.OnClickListener, PlusContact.View, BDLocationListener {
    @BindView(R.id.arrow)
    ImageView arrow;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.register)
    TextView register;
    @BindView(R.id.tv_kind_court)
    TextView tvKindCourt;
    @BindView(R.id.tv_public_time)
    TextView tvPublicTime;
    @BindView(R.id.et_time)
    EditText etTime;
    @BindView(R.id.ll_public_time)
    LinearLayout llPublicTime;
    @BindView(R.id.tv_public_address)
    TextView tvPublicAddress;
    @BindView(R.id.et_address)
    EditText etAddress;
    @BindView(R.id.ll_public_address)
    LinearLayout llPublicAddress;
    @BindView(R.id.tv_people_num)
    TextView tvPeopleNum;
    @BindView(R.id.et_number)
    EditText etNumber;
    @BindView(R.id.ll_people_num)
    LinearLayout llPeopleNum;
    @BindView(R.id.cb_have)
    CheckBox cbHave;
    @BindView(R.id.cb_not_have)
    CheckBox cbNotHave;
    @BindView(R.id.activity_public_article)
    LinearLayout activityPublicArticle;
    @BindView(R.id.post_msg)
    EditText postMsg;

    private PlusPresenter presenter = new PlusPresenter(this);
    private static double longitude;
    private static double latitude;
    public LocationClient mLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocationClient=new LocationClient(getApplicationContext());
        mLocationClient.registerLocationListener(new PlusActivity());

        setContentView(R.layout.activity_post_message);
        ButterKnife.bind(this);

        initData();
        initEvent();


    }

    private void initEvent() {
        arrow.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    private void initData() {
        tvTitle.setText("发帖");
        register.setText("发表");
        tvKindCourt.setText("3 VS 3");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.arrow:
                finish();
            case R.id.register:
                Integer allNumber = Integer.parseInt(etNumber.getText().toString().trim());
                String time = etTime.getText().toString().trim();
                Integer type = 3;
                String context = postMsg.getText().toString().trim();
                presenter.post(allNumber, longitude, latitude, time, type, context, true, Utility.getToken());
                break;
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        requestLocation();
    }

    private void requestLocation(){
        mLocationClient.start();
    }

    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        longitude = bdLocation.getLongitude();
        latitude = bdLocation.getLatitude();
    }


    @Override
    public void onDestroy(){
        super.onDestroy();
        mLocationClient.stop();
    }

    @Override
    public void showToast(final String meg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Utility.makeToast(meg, Toast.LENGTH_SHORT);
            }
        });
    }

    @Override
    public void next() {
        EventBus.getDefault().postSticky(IsLogged.MARK);
        EventBus.getDefault().postSticky(IsPromise.PROMISE);
        Intent intent = new Intent(this, HomeActivity.class);
        intent.putExtra("page",0);
        startActivity(intent);

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
