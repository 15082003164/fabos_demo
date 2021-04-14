package com.cxxy.bysj.service;



import com.cxxy.bysj.entity.*;

import java.util.List;


public interface OrderService {
    public void insertOrder(Order order);

    public void deleteById(Integer orderid);

    public List<Order> selectOrderByExample(OrderExample orderExample);

    public List<OrderItem> getOrderItemByExample(OrderItemExample orderItemExample);

    public void updateOrderByKey(Order order);

    public Order selectByPrimaryKey(Integer orderid);

    public void insertOrderItem(OrderItem orderItem);

}
