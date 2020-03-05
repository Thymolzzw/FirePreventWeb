package com.example.demo.entity;

import java.sql.Timestamp;

public class AlarmRecord {
    private int almRecID;
    private Timestamp almTime;
    private Double opLocX;
    private Double opLocY;
    private int isHandled;
    private int staffID;

    public int getAlmRecID() {
        return almRecID;
    }

    public void setAlmRecID(int almRecID) {
        this.almRecID = almRecID;
    }

    public Timestamp getAlmTime() {
        return almTime;
    }

    public void setAlmTime(Timestamp almTime) {
        this.almTime = almTime;
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

    public int getIsHandled() {
        return isHandled;
    }

    public void setIsHandled(int isHandled) {
        this.isHandled = isHandled;
    }

    public int getStaffID() {
        return staffID;
    }

    public void setStaffID(int staffID) {
        this.staffID = staffID;
    }
}
