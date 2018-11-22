package com.example.dealball.main.dao;

import com.example.dealball.main.bean.Promise;

import org.litepal.crud.DataSupport;

import java.util.List;

public class PromiseDao {
    public static Promise promiseDao(int promiseId){
        List<Promise> promise = DataSupport.where("promiseId = ?", String.valueOf(promiseId) ).find(Promise.class);
        return promise.get(0);
    }

    //从数据库里找出所有的球贴显示 把它们都添加到地图的list中

}
