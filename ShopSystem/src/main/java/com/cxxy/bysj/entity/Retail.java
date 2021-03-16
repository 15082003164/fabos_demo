package com.cxxy.bysj.entity;

public class Retail {
    private Integer id;

    private String username; //用于和user表关联

    private Double total_cash_price;//可提现金额

    private Double this_cash_price;//本次分销获得金额


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Double getTotal_cash_price() {
        return total_cash_price;
    }

    public void setTotal_cash_price(Double total_cash_price) {
        this.total_cash_price = total_cash_price == null ? 0 :total_cash_price;
    }

    public Double getThis_cash_price() {
        return this_cash_price;
    }

    public void setThis_cash_price(Double this_cash_price) {
        this.this_cash_price = this_cash_price == null ? 0 :this_cash_price;;
    }

    @Override
    public String toString() {
        return "Retail{" +
                "id=" + id +
                ", username=" + username +
                ", total_cash_price=" + total_cash_price +
                ", this_cash_price=" + this_cash_price +
                '}';
    }
}
