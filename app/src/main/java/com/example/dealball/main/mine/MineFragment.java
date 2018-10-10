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
import com.example.dealball.main.hello.LoginBYActivity;
import com.example.dealball.main.mine.info.MyInfoActivity;
import com.example.dealball.main.utils.Utility;

import de.hdodenhof.circleimageview.CircleImageView;

public class MineFragment extends BaseFragment implements View.OnClickListener {

    private Button choose_login;
    private RelativeLayout trans_t_rl;
    private RelativeLayout trans_rl;
    private View mineView;
    private  TextView title;
    private TabLayout mine_tab;
    private ViewPager mine_viewpager;

    private CircleImageView my_user_photo;
    private TextView nickname;
    private RelativeLayout add_friends_el;
    private RelativeLayout new_deal_el;
    private RelativeLayout news_el;

    private static final String TAG = MineFragment.class.getSimpleName();

    private boolean isFirst = true;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mineView = inflater.inflate(R.layout.mine_fragment,container, false);

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
        notifyDataSetChanged();

    }

    private void initView() {
        title = mineView.findViewById(R.id.tv_title);
        choose_login = mineView.findViewById(R.id.choose_login);


        trans_t_rl = mineView.findViewById(R.id.trans_t_rl);
        trans_rl = mineView.findViewById(R.id.trans_rl);

        mine_tab = mineView.findViewById(R.id.mine_tab);
        mine_viewpager = mineView.findViewById(R.id.mine_viewpager);
        my_user_photo=mineView.findViewById(R.id.my_user_photo);
        nickname = mineView.findViewById(R.id.nickname);

        add_friends_el = mineView.findViewById(R.id.add_friends_el);
        new_deal_el = mineView.findViewById(R.id.new_deal_el);
        news_el = mineView.findViewById(R.id.news_el);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.choose_login:
                Intent intent=new Intent(getActivity(), LoginBYActivity.class);
                startActivity(intent);
                break;
            case R.id.my_user_photo:
                Intent intent1=new Intent(getActivity(), MyInfoActivity.class);
                startActivity(intent1);
                break;

        }
    }

    /*@Override
    protected void onFragmentVisibleChange(boolean isVisible){
        if(isVisible){
            notifyDataSetChanged();
        }
    }*/

    private void notifyDataSetChanged() {
        if(Utility.getUserById() != 0){
            trans_rl.setVisibility(View.GONE);
            trans_t_rl.setVisibility(View.VISIBLE);
        }

    }

   @Override
    protected void onFragmentFirstVisible(){

        if(Utility.getUserById() == 0){

            Toast.makeText(getContext(),"首次可见",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onResume(){
        super.onResume();
        System.out.println("onResume");

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
    @Override
    public void onPause(){
        super.onPause();
        System.out.println("onPause");
    }

}
