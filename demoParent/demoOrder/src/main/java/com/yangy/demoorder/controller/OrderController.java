package com.yangy.demoorder.controller;

import com.yangy.demoorder.bean.Order;
import com.yangy.demoorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/reduceStorage", method = RequestMethod.POST)
    public String addOrder(@RequestBody Order order) {
        int result = orderService.reduceStorage(order);
        return result == 1 ? "下单成功" : "下单失败";
    }
}
