package com.example.dealball.main.mine;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dealball.R;
import com.example.dealball.main.base.BaseFragment;
import com.example.dealball.main.bean.IsLogged;
import com.example.dealball.main.hello.LoginBYActivity;
import com.example.dealball.main.mine.info.MyInfoActivity;
import com.example.dealball.main.utils.Utility;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private Button choose_login;
    private RelativeLayout trans_t_rl;
    private RelativeLayout trans_rl;
    private View mineView;
    private TextView title;
    private TabLayout mine_tab;
    private ViewPager mine_viewpager;

    private CircleImageView my_user_photo;
    private TextView nickname;
    private TextView ball_age;
    private TextView mine_attack;

    private RelativeLayout add_friends_el;
    private RelativeLayout new_deal_el;
    private RelativeLayout news_el;

    private static final String TAG = MineFragment.class.getSimpleName();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mineView = inflater.inflate(R.layout.mine_fragment, container, false);

        initView();
        initData();
        initEvent();
        return mineView;
    }

    private void initEvent() {
        choose_login.setOnClickListener(this);
        my_user_photo.setOnClickListener(this);
        add_friends_el.setOnClickListener(this);
        new_deal_el.setOnClickListener(this);
        news_el.setOnClickListener(this);

    }

    private void initData() {
        title.setText("我的");
        mine_viewpager.setAdapter(new MineTabAdapter(getChildFragmentManager()));
        mine_tab.setupWithViewPager(mine_viewpager);

    }

    private void initView() {
        title = mineView.findViewById(R.id.tv_title);
        choose_login = mineView.findViewById(R.id.choose_login);

        trans_t_rl = mineView.findViewById(R.id.trans_t_rl);
        trans_rl = mineView.findViewById(R.id.trans_rl);

        mine_tab = mineView.findViewById(R.id.mine_tab);
        mine_viewpager = mineView.findViewById(R.id.mine_viewpager);
        my_user_photo = mineView.findViewById(R.id.my_user_photo);
        nickname = mineView.findViewById(R.id.nickname);
        ball_age = mineView.findViewById(R.id.ball_age);
        mine_attack = mineView.findViewById(R.id.mine_attack);

        add_friends_el = mineView.findViewById(R.id.add_friends_el);
        new_deal_el = mineView.findViewById(R.id.new_deal_el);
        news_el = mineView.findViewById(R.id.news_el);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.choose_login:
                Intent intent = new Intent(getActivity(), LoginBYActivity.class);
                startActivity(intent);
                break;
            case R.id.my_user_photo:
                Intent intent1 = new Intent(getActivity(), MyInfoActivity.class);
                startActivity(intent1);
                break;

        }
    }

    private void notifyDataSetChanged() {

        trans_rl.setVisibility(View.GONE);
        trans_t_rl.setVisibility(View.VISIBLE);
        mine_attack.setText(String.valueOf(Utility.getMyInfoBean().getAttack()));
        ball_age.setText(String.valueOf(Utility.getMyInfoBean().getBallYear()));
        if (Utility.getMyInfoBean().getNickname() == null) {
            nickname.setText("YueQiu" + Utility.getUserById());
        } else {
            nickname.setText(Utility.getMyInfoBean().getNickname());
        }

        //nickname.setText("我是渣渣");

    }

    @Override
    protected void onFragmentVisibleChange(boolean isVisible) {
        notifyDataSetChanged();
    }

    @Override
    protected void onFragmentFirstVisible() {
        Toast.makeText(getContext(), "首次可见", Toast.LENGTH_SHORT).show();
    }

}


//    @Override  fragment单独使用时调用此方法进行ui的操作
//    public void onHiddenChanged(boolean hidden){
//        super.onHiddenChanged(hidden);
//        if (!hidden){
//            System.out.println("跳转显示失败");
//        }else {
//            trans_t_rl.setVisibility(View.VISIBLE);
//            trans_rl.setVisibility(View.GONE);
//            System.out.println("跳转显示成功");
//
//
//        }
//    }

    //    @Override  封装到了基类里 实现登录状态的保存
//    public void onResume(){
//        super.onResume();
//        System.out.println("onResume");
//        EventBus.getDefault().register(this);
//    }
//
//    @Override
//    public void onPause(){
//        super.onPause();
//        System.out.println("onPause");
//        EventBus.getDefault().removeAllStickyEvents();
//        EventBus.getDefault().unregister(this);
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
//    public void IsLoggedBus(IsLogged isLogged){
//            if(isLogged.getName() != null){
//                System.out.println(isLogged.getName());
//                notifyDataSetChanged();
//            }
//    }
