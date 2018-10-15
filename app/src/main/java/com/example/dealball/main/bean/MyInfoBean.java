package com.example.dealball.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

import java.util.Arrays;


public class MyInfoBean extends DataSupport implements Parcelable{
    private int userId;
    private String phone;
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

    public synchronized boolean saveOrUpdate(int userId) {
        return saveOrUpdate("userId = ?", String.valueOf(userId) );
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
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.userId);
        dest.writeString(this.phone);
        dest.writeString(this.nickname);
        dest.writeString(this.sex);
        dest.writeString(this.sign);
        dest.writeFloat(this.attack);
        dest.writeFloat(this.defensive);
        dest.writeInt(this.ballYear);
        dest.writeInt(this.rank);
        dest.writeByteArray(this.avatar);
        dest.writeParcelable(this.physicalData, flags);
    }

    public MyInfoBean() {
    }

    protected MyInfoBean(Parcel in) {
        this.userId = in.readInt();
        this.phone = in.readString();
        this.nickname = in.readString();
        this.sex = in.readString();
        this.sign = in.readString();
        this.attack = in.readFloat();
        this.defensive = in.readFloat();
        this.ballYear = in.readInt();
        this.rank = in.readInt();
        this.avatar = in.createByteArray();
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
