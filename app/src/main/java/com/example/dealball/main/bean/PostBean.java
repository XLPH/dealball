package com.example.dealball.main.bean;

import android.os.Parcel;
import android.os.Parcelable;

import org.litepal.crud.DataSupport;

public class PostBean extends DataSupport implements Parcelable {
    private Integer promiseId;
    private String avatar;
    private Double longitude;
    private Double latitude;

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

    public PostBean() {
    }

    public PostBean(Integer promiseId, Double longitude, Double latitude) {
        this.promiseId = promiseId;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public PostBean(Integer promiseId, String avatar, Double longitude, Double latitude) {
        this.promiseId = promiseId;
        this.avatar = avatar;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Integer getPromiseId() {
        return promiseId;
    }

    public void setPromiseId(Integer promiseId) {
        this.promiseId = promiseId;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "PostBean{" +
                "promiseId=" + promiseId +
                ", avatar=" + avatar +
                '}';
    }

    public synchronized boolean saveOrUpdate(int promiseId) {
        return saveOrUpdate("promiseId = ?", String.valueOf(promiseId) );
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.promiseId);
        dest.writeString(this.avatar);
        dest.writeValue(this.longitude);
        dest.writeValue(this.latitude);
    }

    protected PostBean(Parcel in) {
        this.promiseId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.avatar = in.readString();
        this.longitude = (Double) in.readValue(Double.class.getClassLoader());
        this.latitude = (Double) in.readValue(Double.class.getClassLoader());
    }

    public static final Creator<PostBean> CREATOR = new Creator<PostBean>() {
        @Override
        public PostBean createFromParcel(Parcel source) {
            return new PostBean(source);
        }

        @Override
        public PostBean[] newArray(int size) {
            return new PostBean[size];
        }
    };
}
