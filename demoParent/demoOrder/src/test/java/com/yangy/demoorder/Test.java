package com.yangy.demoorder;

import com.yangy.demoorder.service.OrderService;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Test {
    @Resource
    private OrderService orderService;

    @org.junit.Test
    public void insert() {
        orderService.insert();
    }
}
