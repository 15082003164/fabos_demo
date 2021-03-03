package com.cxxy.demo;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.bind.annotation.RequestMapping;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class Test {

    @org.junit.Test
    @RequestMapping
    public void testPlan(){
        int i = 1/0;
    }
}
