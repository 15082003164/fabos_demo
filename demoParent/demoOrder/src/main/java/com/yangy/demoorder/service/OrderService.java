package com.yangy.demoorder.service;

import com.yangy.demoorder.bean.Order;
import com.yangy.demoorder.dao.OrderDao;
import com.yangy.demoorder.dao.StorageFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Transactional
@Service
public class OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private StorageFeignClient storageFeignClient;

    public int reduceStorage(Order order) {

        //订单的主键用UUID生成
        String orderId = UUID.randomUUID().toString().replaceAll("-", "");

        order.setOrderId(orderId);
        order.setUserId(order.getUserId());
        order.setStorageId(order.getStorageId());
        order.setQuantity(order.getQuantity());
//        int result = orderDao.insertSelective(order);
          int result = orderDao.insert(order);
        storageFeignClient.reduceStorage(order.getStorageId(), order.getQuantity());
        return result;

    }

    public void insert(){
        Order order = new Order();
        order.setOrderId("bbd");
        order.setUserId(10);
        order.setStorageId("456");
        order.setQuantity(6);
        orderDao.insert(order);
    }
}
