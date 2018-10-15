package com.example.dealball.main.mine.info;

import android.app.Activity;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dealball.R;
import com.example.dealball.main.hello.LoginBYActivity;
import com.example.dealball.main.utils.Utility;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoActivity extends Activity implements View.OnClickListener {
    private RelativeLayout changeUserPhoto;
    private CircleImageView civUserHeader;
    private RelativeLayout rlUserNick;
    private TextView userNickname;
    private RelativeLayout rlUserSex;
    private TextView userSex;
    private TextView userSign;
    private RelativeLayout rlUserPhone;
    private TextView userPhone;
    private TextView isBind;
    private RelativeLayout rlUserWeChat;
    private TextView userWeChat;
    private TextView isWeChatBind;
    private  TextView title;
    private ImageView arrow;
    private TextView register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_detail_message);

        initView();
        initData();
        initEvent();

    }
    private void initView() {
        title = findViewById( R.id.tv_title);
        arrow = findViewById( R.id.arrow );
        register = findViewById(R.id.register);
        changeUserPhoto = findViewById( R.id.change_user_photo );
        civUserHeader = findViewById( R.id.civ_user_header );
        rlUserNick = findViewById( R.id.rl_user_nick );
        userNickname =findViewById( R.id.user_nickname );
        rlUserSex = findViewById( R.id.rl_user_sex );
        userSex = findViewById( R.id.user_sex );
        userSign = findViewById( R.id.user_sign );
        rlUserPhone = findViewById( R.id.rl_user_phone );
        userPhone = findViewById( R.id.user_phone );
        isBind = findViewById( R.id.is_bind );
        rlUserWeChat = findViewById( R.id.rl_user_we_chat );
        userWeChat = findViewById( R.id.user_we_chat );
        isWeChatBind = findViewById( R.id.is_we_chat_bind );
    }

    private void initData() {
        title.setText("我的资料");
        register.setText("完成");
    }

    @Override
    public void onResume(){
        super.onResume();
        showDetail();
    }

    private void showDetail() {
        if (Utility.getMyInfoBean().getNickname() == null){
            userNickname.setText("YueQiu" + Utility.getUserById());
        }else {
            userNickname.setText(Utility.getMyInfoBean().getNickname());
        }
        userSex.setText(Utility.getMyInfoBean().getSex());
    }

    private void initEvent() {
        arrow.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_user_nick:
                break;

            case R.id.arrow:
                finish();

        }
    }
}
