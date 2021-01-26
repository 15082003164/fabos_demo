package com.yangy.demoorder.dao;

import com.yangy.demoorder.Mapper.MyMapper;
import com.yangy.demoorder.bean.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OrderDao extends MyMapper<Order> {

}
