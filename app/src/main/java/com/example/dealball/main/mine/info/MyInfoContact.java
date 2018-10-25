package com.example.dealball.main.mine.info;

import com.example.dealball.main.base.BaseUiOperate;

public interface MyInfoContact {
    interface View extends BaseUiOperate{
        void updateMessage(String updateName, String msg);
        void showToast(String meg);

    }
    interface Presenter{
        void updateInfo(String updateName, String sex, String token);
    }
}
