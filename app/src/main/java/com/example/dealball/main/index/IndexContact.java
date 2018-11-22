package com.example.dealball.main.index;

import com.example.dealball.main.base.BaseView;

interface IndexContact {
    interface View extends BaseView{
        void next();
    }
    interface Presenter{
        void joinBall(String promiseId, String haveBall, String token);

    }
}
