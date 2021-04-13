package com.cxxy.bysj.entity;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Goods {
    private Integer goodsid;

    private String goodsname;

    private Integer price;

    private Integer num;

    private String makings_1;

    private String makings_2;

    private String makings_3;

    private Date uptime;

    private Integer category;

    private String detailcate;

    private Integer activityid;

    private String description;

    private List<ImagePath> imagePaths;

    private Activity activity;

    private Float newPrice;

    private Integer goods_num;

    public void setMakings_1(String makings_1) {
        this.makings_1 = makings_1 == null ? null :makings_1.trim();
    }

    public void setMakings_2(String makings_2) {
        this.makings_2 = makings_2 == null ? null : makings_2.trim();
    }

    public void setMakings_3(String makings_3) {
        this.makings_3 = makings_3 == null ? null : makings_3.trim();
    }

    public void setGoodsname(String goodsname) {
        this.goodsname = goodsname == null ? null : goodsname.trim();
    }

    public void setDetailcate(String detailcate) {
        this.detailcate = detailcate == null ? null : detailcate.trim();
    }


    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

}