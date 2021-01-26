package com.yangy.springboot.dao;

import com.yangy.springboot.Mapper.MyMapper;
import com.yangy.springboot.model.UserRole;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserRoleDao extends MyMapper<UserRole> {
//
//        @Insert("insert into user_role(id,role_id) values(#{userId},#{roleId})")
//        List<Integer> bondUserAndRole(UserRole userRole);
//
//        @Delete("delete from user_role where id = #{userId} and role_id = #{roleId}")
//        void unbondUserAndRole(UserRole userRole);

}
