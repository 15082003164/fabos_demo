package com.cxxy.bysj.dao;

import com.cxxy.bysj.entity.Sale;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SaleMapper {

    @Select("select goods_id, goods_name, goods_num, buy_time from sale " +
            "order by goods_num DESC")
    List<Sale> selectAll();

    @Insert("insert sale(goods_id, goods_name, goods_num, buy_time) " +
            "values(#{goods_id}, #{goods_name}, #{goods_num}, #{buy_time})")
    int insertByGoodsId(Sale sale);

    @Delete("delete from sale where goods_id = #{goods_id}")
    int deleteByGoodsId(Integer goods_id);
}
