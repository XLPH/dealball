package com.example.dealball.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

public class PhysicalData extends DataSupport implements Parcelable {
    private Integer userId;
    private Double height;
    private Double weight;
    //习惯用手
    private String userHand;
    /**
     *打球位置
     * 1： 中锋
     * 2： 大前锋
     * 3： 小前锋
     * 4： 得分后卫
     * 5： 控球后卫
     */
    private Integer playPosition;
    //是否公开身体数据
    private Boolean isPublic;

    public PhysicalData(Integer userId, Double height, Double weight, String userHand, Integer playPosition, Boolean isPublic) {
        this.userId = userId;
        this.height = height;
        this.weight = weight;
        this.userHand = userHand;
        this.playPosition = playPosition;
        this.isPublic = isPublic;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public String getUserHand() {
        return userHand;
    }

    public void setUserHand(String userHand) {
        this.userHand = userHand;
    }

    public Integer getPlayPosition() {
        return playPosition;
    }

    public void setPlayPosition(Integer playPosition) {
        this.playPosition = playPosition;
    }

    public Boolean getPublic() {
        return isPublic;
    }

    public void setPublic(Boolean aPublic) {
        isPublic = aPublic;
    }

    @Override
    public String toString() {
        return "PhysicalData{" +
                "userId=" + userId +
                ", height=" + height +
                ", weight=" + weight +
                ", userHand='" + userHand + '\'' +
                ", playPosition=" + playPosition +
                ", isPublic=" + isPublic +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.userId);
        dest.writeValue(this.height);
        dest.writeValue(this.weight);
        dest.writeString(this.userHand);
        dest.writeValue(this.playPosition);
        dest.writeValue(this.isPublic);
    }

    protected PhysicalData(Parcel in) {
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.height = (Double) in.readValue(Double.class.getClassLoader());
        this.weight = (Double) in.readValue(Double.class.getClassLoader());
        this.userHand = in.readString();
        this.playPosition = (Integer) in.readValue(Integer.class.getClassLoader());
        this.isPublic = (Boolean) in.readValue(Boolean.class.getClassLoader());
    }

    public static final Creator<PhysicalData> CREATOR = new Creator<PhysicalData>() {
        @Override
        public PhysicalData createFromParcel(Parcel source) {
            return new PhysicalData(source);
        }

        @Override
        public PhysicalData[] newArray(int size) {
            return new PhysicalData[size];
        }
    };
}
