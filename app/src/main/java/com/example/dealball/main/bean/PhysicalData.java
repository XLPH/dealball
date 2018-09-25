package com.example.dealball.main.bean;

public class PhysicalData {
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
}
