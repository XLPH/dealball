package com.example.dealball.main.plus;

import com.example.dealball.main.base.BaseUiOperate;
import com.example.dealball.main.base.BaseView;

import java.util.Date;

public interface PlusContact {

    interface View extends BaseUiOperate {
        void showToast(String meg);
    }
    interface Presenter{
        void post(Integer allNumber, double longitude, double latitude, String time, Integer type, String context, boolean haveBall, String token);

    }

}
