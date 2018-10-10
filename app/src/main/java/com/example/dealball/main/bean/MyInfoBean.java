package com.example.dealball.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

//implements Parcelable
public class MyInfoBean {
    private int userId;
    private String phone;
    //private String portrait;
    private String nickname;
    private String sex;
    private String sign;

    private float attack;
    private float defensive;
    private int ballYear;
    private int rank;
    private byte[] avatar;

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

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
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

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public PhysicalData getPhysicalData() {
        return physicalData;
    }

    public void setPhysicalData(PhysicalData physicalData) {
        this.physicalData = physicalData;
    }

    @Override
    public String toString() {
        return "MyInfoBean{" +
                "userId=" + userId +
                ", phone='" + phone + '\'' +
                ", nickname='" + nickname + '\'' +
                ", sex='" + sex + '\'' +
                ", sign='" + sign + '\'' +
                ", attack=" + attack +
                ", defensive=" + defensive +
                ", ballYear=" + ballYear +
                ", rank=" + rank +
                ", avatar=" + Arrays.toString(avatar) +
                ", physicalData=" + physicalData +
                '}';
    }
    /*  public static Creator<MyInfoBean> getCREATOR() {
        return CREATOR;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {   //序列化
        parcel.writeInt(id);
        parcel.writeString(portrait);
        parcel.writeString(nickname);
        parcel.writeString(sex);
        parcel.writeString(sign);

    }
    protected MyInfoBean(Parcel in) {
        id=in.readInt();
        portrait=in.readString();
        nickname=in.readString();
        sex=in.readString();
        sign=in.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<MyInfoBean> CREATOR = new Creator<MyInfoBean>() { //反序列化
        @Override
        public MyInfoBean createFromParcel(Parcel in) {
            return new MyInfoBean(in);
        }

        @Override
        public MyInfoBean[] newArray(int size) {
            return new MyInfoBean[size];
        }
    };*/




}
