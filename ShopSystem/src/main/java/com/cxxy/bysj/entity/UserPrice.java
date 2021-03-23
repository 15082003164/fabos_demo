package com.cxxy.bysj.entity;

public class UserPrice {


    private String username;

    private Double user_total_price;

    private Double user_price;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getUser_total_price() {
        return user_total_price;
    }

    public void setUser_total_price(Double user_total_price) {
        this.user_total_price = user_total_price;
    }

    public Double getUser_price() {
        return user_price;
    }

    public void setUser_price(Double user_price) {
        this.user_price = user_price;
    }

    @Override
    public String toString() {
        return "UserPrice{" +
                "username='" + username + '\'' +
                ", user_total_price=" + user_total_price +
                ", user_price=" + user_price +
                '}';
    }
}
