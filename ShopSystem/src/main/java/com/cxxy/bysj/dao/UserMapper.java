package com.cxxy.bysj.dao;


import com.cxxy.bysj.entity.User;
import com.cxxy.bysj.entity.UserExample;
import com.cxxy.bysj.entity.UserRelation;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer userid);

    int deleteUserRelationByChild(String user_children_id);
    int insert(User record);

    int insertSelective(User record);

    int insertSelectiveForRelation(UserRelation userRelation);

    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer userid);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<String> getUsernameBySelect();

    int CountByUserChildrenId(String user_children_id);

    String getUserParentIdByUserName(String username);
}