package com.cxxy.bysj.dao;

import com.cxxy.bysj.entity.RetailConfig;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface RetailMapper {

    @Select("select id,retail_first_percent,retail_second_percent,retail_third_percent,price_config from retailconfig")
    List<RetailConfig> selectConfig();

    @Update("update retailconfig set retail_first_percent=#{retail_first_percent},retail_second_percent=#{retail_second_percent},"
            +"retail_third_percent=#{retail_third_percent},price_config=#{price_config}")
    int updateConfig(RetailConfig retailConfig);
}
