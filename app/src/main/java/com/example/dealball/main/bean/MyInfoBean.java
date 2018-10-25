package com.example.dealball.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.Arrays;


public class MyInfoBean extends DataSupport implements Parcelable{
    private int userId;
    private String phone;
    private String nickName;
    private String sex;
    private String signature;

    private float attack;
    private float defensive;
    private int ballYear;
    private int ranking;
    private String avatar;

    private PhysicalData physicalData;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public float getAttack() {
        return attack;
    }

    public void setAttack(float attack) {
        this.attack = attack;
    }

    public float getDefensive() {
        return defensive;
    }

    public void setDefensive(float defensive) {
        this.defensive = defensive;
    }

    public int getBallYear() {
        return ballYear;
    }

    public void setBallYear(int ballYear) {
        this.ballYear = ballYear;
    }

    public int getRanking() {
        return ranking;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public PhysicalData getPhysicalData() {
        return physicalData;
    }

    public void setPhysicalData(PhysicalData physicalData) {
        this.physicalData = physicalData;
    }

    public synchronized boolean saveOrUpdate(int userId) {
        return saveOrUpdate("userId = ?", String.valueOf(userId) );
    }

    @Override
    public String toString() {
        return "MyInfoBean{" +
                "userId=" + userId +
                ", phone='" + phone + '\'' +
                ", nickName='" + nickName + '\'' +
                ", sex='" + sex + '\'' +
                ", signature='" + signature + '\'' +
                ", attack=" + attack +
                ", defensive=" + defensive +
                ", ballYear=" + ballYear +
                ", rank=" + ranking +
                ", avatar=" + avatar +
                ", physicalData=" + physicalData +
                '}';
    }
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.phone);
        dest.writeString(this.nickName);
        dest.writeString(this.sex);
        dest.writeString(this.signature);
        dest.writeFloat(this.attack);
        dest.writeFloat(this.defensive);
        dest.writeInt(this.ballYear);
        dest.writeInt(this.ranking);
        dest.writeString(this.avatar);
//        dest.writeByteArray(this.avatar);
        dest.writeParcelable(this.physicalData, flags);
    }

    public MyInfoBean() {
    }

    protected MyInfoBean(Parcel in) {
        this.userId = in.readInt();
        this.phone = in.readString();
        this.nickName = in.readString();
        this.sex = in.readString();
        this.signature = in.readString();
        this.attack = in.readFloat();
        this.defensive = in.readFloat();
        this.ballYear = in.readInt();
        this.ranking = in.readInt();
        this.avatar = in.readString();
//        this.avatar = in.createByteArray();
        this.physicalData = in.readParcelable(PhysicalData.class.getClassLoader());
    }

    public static final Creator<MyInfoBean> CREATOR = new Creator<MyInfoBean>() {
        @Override
        public MyInfoBean createFromParcel(Parcel source) {
            return new MyInfoBean(source);
        }

        @Override
        public MyInfoBean[] newArray(int size) {
            return new MyInfoBean[size];
        }
    };
}
