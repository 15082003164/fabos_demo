package com.cxxy.demo.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "shop")
public class Shop extends BaseDomain {

    private static final long serialVersionUID = -460922993085630428L;
    //商品id，商品名，商品数量，价格，分销商id
    @Id
    private int shop_id;

    private String shop_name;
    private int shop_number;
    private double shop_price;
    private int user_id;

}
