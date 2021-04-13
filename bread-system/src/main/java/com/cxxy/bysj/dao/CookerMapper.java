package com.cxxy.bysj.dao;

import com.cxxy.bysj.entity.Cooker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CookerMapper {

    @Select("select cookerId, cookerName, password from cooker where cookerName = #{cookerName} and password = #{password}")
    Cooker selectByName(Cooker cooker);
}
