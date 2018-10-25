package com.example.dealball.main.mine.info;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.dealball.R;
import com.example.dealball.main.utils.DialogUtils;
import com.example.dealball.main.utils.ImageUtil;
import com.example.dealball.main.utils.Utility;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyInfoActivity extends Activity implements View.OnClickListener, MyInfoContact.View {
    private RelativeLayout changeUserPhoto;
    private CircleImageView civUserHeader;
    private RelativeLayout rlUserNick;
    private TextView userNickname;
    private RelativeLayout rlUserSex;
    private TextView userSex;
    private EditText userSign;
    private CardView cv_sign;
    private DialogUtils dialogInput ;
    private CardView cv_input;
    private TextView tv_title;
    private EditText et_message;
    private Button cancel;
    private Button confirm;

    private RelativeLayout rlUserPhone;
    private TextView userPhone;
    private TextView isBind;
    private RelativeLayout rlUserWeChat;
    private TextView userWeChat;
    private TextView isWeChatBind;
    private  TextView title;
    private ImageView arrow;
    private TextView register;

    private MyInfoPresenter presenter;
    private DialogUtils dialog ;
    private TextView tv_album;
    private TextView tv_camera;
    private TextView tv_bd_cancel;
    public static final int TAKE_PHOTO =3;
    public static final int CHOOSE_PHOTO =2;

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
        cv_sign = findViewById(R.id.cv_sign);
        rlUserPhone = findViewById( R.id.rl_user_phone );
        userPhone = findViewById( R.id.user_phone );
        isBind = findViewById( R.id.is_bind );
        rlUserWeChat = findViewById( R.id.rl_user_we_chat );
        userWeChat = findViewById( R.id.user_we_chat );
        isWeChatBind = findViewById( R.id.is_we_chat_bind );
        presenter = new MyInfoPresenter(this);
        dialog = new DialogUtils(this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, R.layout.bottom_dialog, R.style.BottomDialog, Gravity.BOTTOM, R.style.BottomDialog_Animation);
        tv_album = dialog.findViewById(R.id.tv_album);
        tv_camera = dialog.findViewById(R.id.tv_camera);
        tv_bd_cancel = dialog.findViewById(R.id.tv_bd_cancel);

        dialogInput = new DialogUtils(this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, R.layout.dialog_input, R.style.BottomDialog, Gravity.BOTTOM, R.style.BottomDialog_Animation);
        cv_input = dialogInput.findViewById(R.id.cv_input);
        et_message = dialogInput.findViewById(R.id.et_message);
        cancel = dialogInput.findViewById(R.id.cancel);
        confirm = dialogInput.findViewById(R.id.confirm);
        tv_title = dialogInput.findViewById(R.id.tv_title);

    }
    private void initData() {
        title.setText("我的资料");
        register.setText("完成");
    }

    private void initEvent() {  //多个控件共享一个监听器
        arrow.setOnClickListener(this);
        rlUserSex.setOnClickListener(this);
        rlUserNick.setOnClickListener(this);
        register.setOnClickListener(this);
        cv_sign.setOnClickListener(this);
        changeUserPhoto.setOnClickListener(this);
        tv_album.setOnClickListener(this);
        tv_camera.setOnClickListener(this);
        tv_bd_cancel.setOnClickListener(this);
        cancel.setOnClickListener(this);
        confirm.setOnClickListener(this);
        cv_input.setOnClickListener(this);
    }

    @Override
    public void onResume(){
        super.onResume();
        showDetail();
    }
    private void showDetail() {
        if (Utility.getMyInfoBean().getNickName() == null){
            userNickname.setText("YueQiu" + Utility.getUserById());
        }else {
            userNickname.setText(Utility.getMyInfoBean().getNickName());
        }
        userSex.setText(Utility.getMyInfoBean().getSex());
        if(Utility.getMyInfoBean().getSignature() == null){
            userSign.setText("编辑个性签名");
        }else{
            userSign.setText(Utility.getMyInfoBean().getSignature());
        }
        if(Utility.getMyInfoBean().getAvatar() != null){
            Glide.with(this).load(ImageUtil.base64StringToByte(Utility.getMyInfoBean().getAvatar())).into(civUserHeader);
        }
    }


    @Override
    public void onClick(android.view.View view) {  //因为自定义的DialogUtils继承了Dialog，Dialog实现了DialogInterface，
        // DialogInterface中有OnClickListener方法，在不同的包内，所以要写全包名
        switch (view.getId()) {
            case R.id.change_user_photo:
                dialog.show();
                break;
            case R.id.tv_album:
                toAlbum();
                break;
            case R.id.rl_user_nick:
                dialogInput.show();
//                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                et_message.setText(userNickname.getText());
                tv_title.setText("请输入昵称");
                et_message.setFocusable(true);
                et_message.setFocusableInTouchMode(true);
                et_message.requestFocus();
                et_message.setSelection(et_message.getText().length());
                /*InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.showSoftInput(et_message, 0);*/
                break;
            case R.id.cancel:
                dialogInput.dismiss();
                break;
            case R.id.confirm:
                dialog.dismiss();
                presenter.updateInfo("nickName",et_message.getText().toString(),Utility.getToken());
                break;
            case R.id.rl_user_sex:
                DialogUtils.showSingleChoiceItems(this, presenter);
                break;
            case R.id.cv_sign:
                userSign.requestFocus();
                userSign.setSelection(userSign.getText().length());
                break;
            case R.id.register:
                presenter.updateInfo("signature",userSign.getText().toString(),Utility.getToken());
                userSign.clearFocus();
                userSign.setFocusable(false);
                break;
            case R.id.arrow:
                finish();

        }
    }

    private void toAlbum() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent,CHOOSE_PHOTO);
        dialog.dismiss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode != RESULT_CANCELED){
            switch (requestCode){
                case CHOOSE_PHOTO:
                    Glide.with(this).load(data.getData()).thumbnail(0.1f).into(civUserHeader);
                    try {
                        Bitmap bitmap1 = ImageUtil.getBitmapFromUri(data.getData(), this);
                        byte[] b = "hello world".getBytes();
                        String s = ImageUtil.byteToBase64String(b);
                        byte[] b1 = ImageUtil.base64StringToByte(s);
                        for(byte b2: b1){
                            System.out.println(b2);
                        }
                        byte[] bytes = ImageUtil.bitmapToBytes(bitmap1);
                        System.out.println(bytes);
//                        presenter.getBytes();
                        presenter.updateInfo("avatar", bytes, Utility.getToken());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case TAKE_PHOTO:
                    break;
            }
        }
    }



    @Override
    public void updateMessage(final String updateName, final String msg) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(updateName.equals("sex")){
                    userSex.setText(msg);
                }
                if(updateName.equals("signature")){
                    userSign.setText(msg);
                }
                if(updateName.equals("nickName")){
                    userNickname.setText(msg);
                }
                if(updateName.equals("avatar")){
                     Glide.with(getApplicationContext()).load(ImageUtil.base64StringToByte(Utility.getMyInfoBean().getAvatar())).into(civUserHeader);
                }
            }
        });


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

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
