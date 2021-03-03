package com.yangy.springboot.dao;

import com.yangy.springboot.model.Purview;
import com.yangy.springboot.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserPurviewDao {
    //    @Select("select purview.purview_name from user inner join user_purview up on up.id = user.id " +
//            "inner join user_role ur on ur.id = user.id inner join role_purview rp on rp.role_id = ur.role_id " +
//            "inner join purview on purview.purview_id = up.purview_id and purview.purview_id = rp.purview_id " +
//            "where user.user_name = #{userName}")
    List<String> getPurviewIdByUser(String username);

    //    @Select("select user.user_name from purview inner join user_purview up on up.purview_id = purview.purview_id " +
//            "inner join user on user.id = up.id where purview.purview_id = #{purviewId} union " +
//            "select user.user_name from purview inner join role_purview rp on rp.purview_id = purview.purview_id " +
//            "inner join user_role ur on ur.role_id = rp.role_id inner join user on user.id = ur.id " +
//            "where purview.purview_id = #{purviewId}")
    List<String> getUserByPurviewId(Integer purviewId);
}
