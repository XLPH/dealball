package com.example.dealball.main.index;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dealball.R;
import com.example.dealball.main.HomeActivity;
import com.example.dealball.main.bean.IsLogged;
import com.example.dealball.main.bean.IsPromise;
import com.example.dealball.main.bean.Promise;
import com.example.dealball.main.dao.MyInfoDao;
import com.example.dealball.main.dao.PromiseDao;
import com.example.dealball.main.utils.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class IndexActivity extends AppCompatActivity implements View.OnClickListener ,
        IndexContact.View {
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.yue_bt_now)
    Button yueBtNow;
    @BindView(R.id.activity_yue_detail)
    RelativeLayout activityYueDetail;
    @BindView(R.id.civ_deal_head)
    CircleImageView civDealHead;
    @BindView(R.id.tv_deal_nick)
    TextView tvDealNick;
    @BindView(R.id.tv_deal_time)
    TextView tvDealTime;
    @BindView(R.id.tv_deal_address)
    TextView tvDealAddress; //后台没有提供地点，等提供了再设值
    @BindView(R.id.tv_deal_ball)
    TextView tvDealBall;
    @BindView(R.id.tv_deal_capacity)
    TextView tvDealCapacity;
    @BindView(R.id.tv_deal_now_number)
    TextView tvDealNowNumber;
    @BindView(R.id.tv_deal_remark)
    TextView tvDealRemark;
    private IndexPresenter presenter = new IndexPresenter(this);
    private static int promiseId1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_detail);
        ButterKnife.bind(this);
        initData();
        initEvent();
    }

    private void initEvent() {
        ivBack.setOnClickListener(this);
        yueBtNow.setOnClickListener(this);
    }

    private void initData() {
        tvTitle.setText("详情");
        Intent intent = getIntent();
        /*Bundle bundle = intent.getExtras();
        int promiseId = bundle.getInt("bundlePromiseId");*/
        int promiseId = intent.getIntExtra("promiseId", -1);
        promiseId1 = promiseId;
        if(promiseId !=0){
            Promise promise = PromiseDao.promiseDao(promiseId);
            int userId = promise.getUserId();
            tvDealNick.setText(MyInfoDao.myInfoDao(userId).getNickName());
            System.out.println("getNickName");
            tvDealTime.setText(promise.getPromiseTime().toString());
            System.out.println("getPromiseTime");
            if (promise.getHaveBall()) {
                tvDealBall.setText("有球");
            } else {
                tvDealBall.setText("没球");
            }
      /*  tvDealCapacity.setText(Utility.getPromise().getAllNumber());
        tvDealNowNumber.setText(Utility.getPromise().getNowNumber());
        tvDealRemark.setText(Utility.getPromise().getPromiseContext());*/
        }else{
            tvDealNick.setText(Utility.getMyInfoBean().getNickName());//自己的昵称
            tvDealTime.setText(Utility.getPromise().getPromiseTime().toString());//暂存在Sharesprefence里自己的发帖信息
            if (Utility.getPromise().getHaveBall()) {
                tvDealBall.setText("有球");
            } else {
                tvDealBall.setText("没球");
            }
        }


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                next(); //返回也要保存信息
                finish();
            case R.id.yue_bt_now:
//                Toast.makeText(this, "已预约", Toast.LENGTH_SHORT).show();//如果promiseId=0，弹出自己不能预约，或者让它不可见
                presenter.joinBall(String.valueOf(promiseId1), "true", Utility.getToken());
                   //finish之后，地图上的图标Marker都没有了，要走onResume()方法，把图标都加载出来
                //最先添加的默认Marker没有了，但是后来创建的带有头像的Marker还有，  实现后来创建的Marker也能点击!  猜想：Marker的生命创建和周期 × 是C语言和C++没学好啊
                //条件判断语句 if{} if{} 和 if{}else if{} 的区别 ，前一个是执行完第一个if{}语句的内容后，接着判断下一个if{}是否满足 ，所以你点击不到后面第二个之后的marker

        }
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
    public void onDestroy() {
        super.onDestroy();
        System.out.println("onDestroy");

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
}
