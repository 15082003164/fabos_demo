package com.cxxy.bysj.dao;

import com.cxxy.bysj.entity.Kinds;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface KindsMapper {

    @Select("select kinds_id, kinds_name, kinds_num from kinds")
    List<Kinds> selectKindById();

    @Insert("insert kinds(kinds_id, kinds_name, kinds_num) values(#{kinds_id}, #{kinds_name}, #{kinds_num}) ")
    int insertKind(Kinds kinds);

    @Update("update kinds set kinds_name = #{kinds_name}, kinds_num = #{kinds_num} where kinds_id = #{kinds_id}")
    int updateKind(Kinds kinds);

    @Delete("delete from kinds where kinds_id = #{kinds_id}")
    int deleteKindById(Integer kinds_id);

}
