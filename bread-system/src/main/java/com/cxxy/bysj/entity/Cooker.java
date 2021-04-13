package com.cxxy.bysj.entity;

import lombok.Data;

@Data
public class Cooker {

    private Integer cookerId;

    private String cookerName;

    private String password;

    public Cooker(Integer cookerId, String cookerName, String password) {
        this.cookerId = cookerId;
        this.cookerName = cookerName;
        this.password = password;
    }

    public Cooker() {
    }

    public void setCookerName(String cookerName) {
        this.cookerName = cookerName == null ? null : cookerName.trim();
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }
}