package com.cxxy.bysj.dao;

import com.cxxy.bysj.entity.Retail;
import com.cxxy.bysj.entity.RetailConfig;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface RetailMapper {

    @Select("select id,retail_first_percent,retail_second_percent,retail_third_percent,price_config from retailconfig")
    List<RetailConfig> selectConfig();

    @Update("update retailconfig set retail_first_percent=#{retail_first_percent},retail_second_percent=#{retail_second_percent},"
            +"retail_third_percent=#{retail_third_percent},price_config=#{price_config}")
    int updateConfig(RetailConfig retailConfig);

    @Insert("insert retail(id ,username ,total_cash_price ,this_cash_price) " +
            "values(#{id}, #{username} ,#{total_cash_price}, #{this_cash_price})")
    int insertRetail(Retail retail);

    @Update("update retail set total_cash_price=#{retail.total_cash_price}, " +
            "this_cash_price=#{retail.this_cash_price} where username = #{username}")
    int updateRetailById(@Param("retail") Retail retail, @Param("username") String username);

    @Select("    select username ,total_cash_price ,this_cash_price" +
            "    from retail" +
            "    where username = #{username}")
    List<Retail> selectRetailByUserId(String username);

    @Select("select total_cash_price from retail where username = #{username}")
    Double selectTotalPriceByUsername(String username);


    /**
     * 用户id(child) -> (根据user_level) -> parent_id(对应到user表中的userId) ->userId(Retail表中的userId) ->商品价格(indent表中的newPrice)*分销比 ->得到本次分销金额
     * -> total_cash_price = total_cash_price + 本次分销金额
     */


//    @Select("select ur.id,ur.user_parent_id,ur.user_children_id,ur.user_level from userrelation ur " +
//            "inner join user on user.username = ur.user_children_id " +
//            "and user.userId = #{userid}")
//    List<UserRelation> SelectUserRelationByUserId(Integer userid);
}
