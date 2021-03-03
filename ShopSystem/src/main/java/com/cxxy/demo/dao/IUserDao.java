package com.cxxy.demo.dao;
import java.util.List;

import com.cxxy.demo.bean.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface IUserDao {

    @Insert("insert into user values(null,#{user_name},#{user_pass},#{user_type},#{name},#{user_phone})")
    public abstract int addUser(User user);

    @Delete("delete from user where user_id=#{user_id}")
    public abstract int delUser(String user_id);

    public abstract int delUsers(String[] user_ids);

    public abstract int updateUser(User user);

    @Select("select * from user")
    public abstract User getUser(User user);

    public abstract List<User>  listUsers(User user);

    public abstract int listUsersCount(User user);

}
