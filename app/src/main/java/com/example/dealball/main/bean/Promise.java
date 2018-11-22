package com.example.dealball.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Promise extends DataSupport implements Parcelable {

    //约球贴id
    private Integer promiseId;
    private Integer userId;
    //该约球贴需要的总人数
    private Integer allNumber;
    //经纬度
    private Double longitude;
    private Double latitude;
    //该约球贴目前已加入人数
    private Integer nowNumber;
    //约定时间
    private Date promiseTime;
    /**
     * 约球类型
     * 1： 斗牛
     * 2： 3V3
     * 3： 全场
     * 4： 其他
     */
    private Integer promiseType;
    //球贴内容
    private String promiseContext;
    //是否有球
    private Boolean haveBall;
    /**
     * 约球是否完成
     * -1： 已取消
     * 1： 已完成
     * 0： 正在进行
     */
    private Integer finish;
    //参与约球的用户
    private List<Integer> members;

    private String geohash;

    public Promise() {

    }

    public String getGeohash() {
        return geohash;
    }

    public void setGeohash(String geohash) {
        this.geohash = geohash;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public List<Integer> getMembers() {
        return members;
    }

    public void setMembers(List<Integer> members) {
        this.members = members;
    }

    public Integer getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(Integer promiseId) {
        this.promiseId = promiseId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getAllNumber() {
        return allNumber;
    }

    public void setAllNumber(Integer allNumber) {
        this.allNumber = allNumber;
    }

    public Integer getNowNumber() {
        return nowNumber;
    }

    public void setNowNumber(Integer nowNumber) {
        this.nowNumber = nowNumber;
    }

    public Date getPromiseTime() {
        return promiseTime;
    }

    public void setPromiseTime(Date promiseTime) {
        this.promiseTime = promiseTime;
    }

    public Integer getPromiseType() {
        return promiseType;
    }

    public void setPromiseType(Integer promiseType) {
        this.promiseType = promiseType;
    }

    public String getPromiseContext() {
        return promiseContext;
    }

    public void setPromiseContext(String promiseContext) {
        this.promiseContext = promiseContext;
    }

    public Boolean getHaveBall() {
        return haveBall;
    }

    public void setHaveBall(Boolean haveBall) {
        this.haveBall = haveBall;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    @Override
    public String toString() {
        return "Promise{" +
                "promiseId=" + promiseId +
                ", userId=" + userId +
                ", allNumber=" + allNumber +
                ", longitude=" + longitude +
                ", latitude=" + latitude +
                ", nowNumber=" + nowNumber +
                ", promiseTime='" + promiseTime + '\'' +
                ", promiseType=" + promiseType +
                ", promiseContext='" + promiseContext + '\'' +
                ", haveBall=" + haveBall +
                ", finish=" + finish +
                ", members=" + members +
                ", geohash='" + geohash + '\'' +
                '}';
    }
    public synchronized boolean saveOrUpdate(int userId) {
        return saveOrUpdate("userId = ?", String.valueOf(userId) );
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.promiseId);
        dest.writeValue(this.userId);
        dest.writeValue(this.allNumber);
        dest.writeValue(this.longitude);
        dest.writeValue(this.latitude);
        dest.writeValue(this.nowNumber);
        dest.writeLong(this.promiseTime != null ? this.promiseTime.getTime() : -1);
        dest.writeValue(this.promiseType);
        dest.writeString(this.promiseContext);
        dest.writeValue(this.haveBall);
        dest.writeValue(this.finish);
        dest.writeList(this.members);
        dest.writeString(this.geohash);
    }

    protected Promise(Parcel in) {
        this.promiseId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.allNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
        this.nowNumber = (Integer) in.readValue(Integer.class.getClassLoader());
        long tmpPromiseTime = in.readLong();
        this.promiseTime = tmpPromiseTime == -1 ? null : new Date(tmpPromiseTime);
        this.promiseType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.promiseContext = in.readString();
        this.haveBall = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.finish = (Integer) in.readValue(Integer.class.getClassLoader());
        this.members = new ArrayList<Integer>();
        in.readList(this.members, Integer.class.getClassLoader());
        this.geohash = in.readString();
    }

    public static final Parcelable.Creator<Promise> CREATOR = new Parcelable.Creator<Promise>() {
        @Override
        public Promise createFromParcel(Parcel source) {
            return new Promise(source);
        }

        @Override
        public Promise[] newArray(int size) {
            return new Promise[size];
        }
    };
}
