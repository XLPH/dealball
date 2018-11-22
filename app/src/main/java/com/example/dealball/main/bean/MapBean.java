package com.example.dealball.main.bean;

public class MapBean {
    private long longitude;
    private long latitude;

    public MapBean(long longitude, long latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }


    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }
}
