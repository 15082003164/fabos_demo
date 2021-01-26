package com.yangy.springboot.dao;

import com.yangy.springboot.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserDao {

    //用户查询
    @Select("select * from user")
    List<User> selectAll();

    //删除用户
    @Delete("delete from user where id=#{id}")
    void deleteUser(Integer id);

    //增加用户
    @Insert("insert into user(username,password) values(#{username},#{password})")
    void insertUser(User user);

    //修改用户
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    void updateUser(User user);

}
