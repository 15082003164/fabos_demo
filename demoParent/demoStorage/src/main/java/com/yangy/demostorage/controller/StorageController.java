package com.yangy.demostorage.controller;

import com.yangy.demostorage.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class StorageController {

    @Autowired
    private StorageService storageService;


    @PostMapping("/reduceStorage")
    public String reduceStorage(@RequestParam String storageId, @RequestParam Integer storageQuantity){
        int result = storageService.reduceStorage(storageId,storageQuantity);
        return result == 1 ? "减少库存成功" : "减少库存失败";
    }


    @PostMapping("/addStorage")
    public String addStorage(){
        int result = storageService.addStorage();
        return result == 1 ? "增加库存成功" : "增加库存失败";
    }
}
