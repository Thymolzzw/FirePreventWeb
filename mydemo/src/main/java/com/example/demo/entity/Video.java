package com.example.demo.entity;

import java.sql.Timestamp;

public class Video {
    private int vidID;
    private String vidName;
    private Timestamp vidStartTime;
    private String vidUrl;
    private String vidSkchImgUrl;
    private int vidType;
    private int staffID;
    private Double opLocX;
    private Double opLocY;

    public int getVidID() {
        return vidID;
    }

    public void setVidID(int vidID) {
        this.vidID = vidID;
    }

    public String getVidName() {
        return vidName;
    }

    public void setVidName(String vidName) {
        this.vidName = vidName;
    }

    public Timestamp getVidStartTime() {
        return vidStartTime;
    }

    public void setVidStartTime(Timestamp vidStartTime) {
        this.vidStartTime = vidStartTime;
    }

    public String getVidUrl() {
        return vidUrl;
    }

    public void setVidUrl(String vidUrl) {
        this.vidUrl = vidUrl;
    }

    public String getVidSkchImgUrl() {
        return vidSkchImgUrl;
    }

    public void setVidSkchImgUrl(String vidSkchImgUrl) {
        this.vidSkchImgUrl = vidSkchImgUrl;
    }

    public int getVidType() {
        return vidType;
    }

    public void setVidType(int vidType) {
        this.vidType = vidType;
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
