package com.cxxy.bysj.entity;

public class RetailConfig {
    private Integer id;

    private Integer retail_first_percent;//一级分销比，单位%

    private Integer retail_second_percent;//二级分销比，单位%

    private Integer retail_third_percent;//三级分销比，单位%

    private double price_config;//最低提现金额

    public Integer getRetail_first_percent() {
        return retail_first_percent;
    }

    public void setRetail_first_percent(Integer retail_first_percent) {
        this.retail_first_percent = retail_first_percent == null ? 0 : retail_first_percent;
    }

    public Integer getRetail_second_percent() {
        return retail_second_percent;
    }

    public void setRetail_second_percent(Integer retail_second_percent) {
        this.retail_second_percent = retail_second_percent == null ? 0 : retail_second_percent;
    }

    public Integer getRetail_third_percent() {
        return retail_third_percent;
    }

    public void setRetail_third_percent(Integer retail_third_percent) {
        this.retail_third_percent = retail_third_percent == null ? 0 : retail_third_percent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getPrice_config() {
        return price_config;
    }

    public void setPrice_config(double price_config) {
        this.price_config = price_config;
    }

    @Override
    public String toString() {
        return "RetailConfig{" +
                "id=" + id +
                ", retail_first_percent=" + retail_first_percent +
                ", retail_second_percent=" + retail_second_percent +
                ", retail_third_percent=" + retail_third_percent +
                ", price_config=" + price_config +
                '}';
    }
}
