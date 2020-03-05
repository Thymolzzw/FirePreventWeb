package com.example.demo.entity;

import java.sql.Timestamp;

public class Picture {
    private int imgID;
    private String imgName;
    private String imgRes;
    private String imgUrl;
    private int imgType;
    private Timestamp createTime;
    private int staffID;
    private Double opLocX;
    private Double opLocY;

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgRes() {
        return imgRes;
    }

    public void setImgRes(String imgRes) {
        this.imgRes = imgRes;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getImgType() {
        return imgType;
    }

    public void setImgType(int imgType) {
        this.imgType = imgType;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }

    public Double getOpLocX() {
        return opLocX;
    }

    public void setOpLocX(Double opLocX) {
        this.opLocX = opLocX;
    }

    public Double getOpLocY() {
        return opLocY;
    }

    public void setOpLocY(Double opLocY) {
        this.opLocY = opLocY;
    }
}
