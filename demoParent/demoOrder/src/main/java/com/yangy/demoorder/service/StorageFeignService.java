package com.yangy.demoorder.service;

import com.yangy.demoorder.dao.StorageFeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

@Component
public class StorageFeignService implements StorageFeignClient {
    @Override
    public String reduceStorage(@RequestParam String storageId, @RequestParam Integer storageQuantity) {

        System.out.println("远程调用Storage模块失败，启动服务熔断");
        throw new RuntimeException("远程调用Storage模块失败，启动服务熔断");

    }

}
