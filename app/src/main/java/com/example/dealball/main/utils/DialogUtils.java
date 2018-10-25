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
    private static final String[] items = new String[]{"男", "女"};
    public DialogUtils(@NonNull Context context) {
        super(context);
    }
    public DialogUtils(Context context,int width, int height, int layout, int style, int gravity, int anim) {
        super(context, style);
        setContentView(layout);
        Window window = getWindow();
        window.setWindowAnimations(anim);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.width = width;
        layoutParams.height = height;
        layoutParams.gravity = gravity;
        window.setAttributes(layoutParams);
    }


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

    public void showBottomDialog(final Context context){

        final Dialog bottomDialog = new Dialog(context, R.style.BottomDialog);
        View contentView = LayoutInflater.from(context).inflate(R.layout.bottom_dialog,null);
        bottomDialog.setContentView(contentView);
        bottomDialog.getWindow().setGravity(Gravity.BOTTOM);
        bottomDialog.getWindow().setWindowAnimations(R.style.BottomDialog_Animation);
        ViewGroup.LayoutParams layoutParams = contentView.getLayoutParams();
        layoutParams.width = context.getResources().getDisplayMetrics().widthPixels;
        contentView.setLayoutParams(layoutParams);
        bottomDialog.show();


    }
}
