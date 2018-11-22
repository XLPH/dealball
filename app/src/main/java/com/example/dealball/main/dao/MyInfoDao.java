package com.example.dealball.main.dao;

import com.example.dealball.main.bean.MyInfoBean;

import org.litepal.crud.DataSupport;

import java.util.List;

public class MyInfoDao {
    public static MyInfoBean myInfoDao(int userId){
        List<MyInfoBean> myInfoBeans = DataSupport.where("userId = ?", String.valueOf(userId) ).find(MyInfoBean.class);//返回的是所有满足条件的集合，点进去看详情
        return myInfoBeans.get(0);
    }
}
