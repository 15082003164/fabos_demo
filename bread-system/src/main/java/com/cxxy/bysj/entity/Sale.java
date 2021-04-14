package com.cxxy.bysj.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Sale {

    private Integer goods_id;

    private String goods_name;

    private Integer goods_num;


    private Date buy_time;

    public Sale(Integer goods_id, String goods_name, Integer goods_num, Date buy_time) {
        this.goods_id = goods_id;
        this.goods_name = goods_name;
        this.goods_num = goods_num;
        this.buy_time = buy_time;
    }
    public Sale(){

    }
}
