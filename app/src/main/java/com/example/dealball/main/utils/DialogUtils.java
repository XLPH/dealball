package com.example.dealball.main.utils;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.dealball.R;
import com.example.dealball.main.mine.info.MyInfoContact;
import com.example.dealball.main.mine.info.MyInfoPresenter;


public class DialogUtils extends Dialog{
//    private final Context context;
    private final int width;
    private final int height;
    private final int layout;
    private final int gravity;
    private final int anim;

    private DialogUtils(@NonNull Context context, int style, Builder builder){
        super(context, style);
//        context = builder.context;
        //style = builder.style;
        width = builder.width;
        height = builder.height;
        layout = builder.layout;
        gravity = builder.gravity;
        anim = builder.anim;

        setContentView(layout);
        Window window = getWindow();
        window.setWindowAnimations(anim);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
    }

    public static class Builder{
        private final Context context;

        private int style = R.style.BottomDialog;
        private int width = WindowManager.LayoutParams.MATCH_PARENT;
        private int height = WindowManager.LayoutParams.WRAP_CONTENT;
        private int layout =  R.layout.bottom_dialog;
        private int gravity = Gravity.BOTTOM;
        private int anim = R.style.BottomDialog_Animation;

        public Builder(Context context){
            this.context = context;
        }

        public Builder style(int value){
            style = value;
            return this;
        }

        public Builder width(int value){
            width = value;
            return this;
        }
        public Builder height(int value){
            height = value;
            return this;
        }
        public Builder layout(int value){
            layout = value;
            return this;
        }
        public Builder gravity(int value){
            gravity = value;
            return this;
        }
        public Builder anim(int value){
            anim = value;
            return this;
        }
        public DialogUtils build(){
            return new DialogUtils(context, style, this);
        }
    }
    private static final String[] items = new String[]{"男", "女"};
    /*public DialogUtils(@NonNull Context context) {
        super(context);
    }

    public DialogUtils(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public DialogUtils(Context context, int width, int height, int layout, int style, int gravity, int anim) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        window.setWindowAnimations(anim);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
    }*/


    public static void showSingleChoiceItems(Context context, final MyInfoPresenter presenter) {

        AlertDialog dialog = new AlertDialog.Builder(context)
                .setSingleChoiceItems(items,0, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        presenter.updateInfo("sex", items[i], Utility.getToken());
                        dialogInterface.dismiss();
                    }
                }).create();
        dialog.show();

    }

}