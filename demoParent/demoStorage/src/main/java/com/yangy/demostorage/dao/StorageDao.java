package com.yangy.demostorage.dao;


import com.yangy.demostorage.Mapper.MyMapper;
import com.yangy.demostorage.bean.Storage;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface StorageDao extends MyMapper<Storage> {
}
