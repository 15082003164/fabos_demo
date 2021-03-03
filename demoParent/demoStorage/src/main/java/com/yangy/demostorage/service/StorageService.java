package com.yangy.demostorage.service;

import com.yangy.demostorage.bean.Storage;
import com.yangy.demostorage.dao.StorageDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;


@Service
public class StorageService {

    @Resource
    private StorageDao storageDao;

    public int reduceStorage(String storageId, Integer storageQuantity) {
        Storage storage = storageDao.selectByPrimaryKey(storageId);
        storage.setStorageQuantity(storage.getStorageQuantity() - storageQuantity);
        int result = storageDao.updateByPrimaryKeySelective(storage);
        return result;
    }

    //增加库存
    public int addStorage() {
        Storage storage = new Storage();
        //仓库Id用UUID生成
        String storageId = UUID.randomUUID().toString().replaceAll("-", "");
        storage.setStorageId(storageId);
        storage.setStuffName("AA");
        storage.setStorageQuantity(30);
        int result = storageDao.insertSelective(storage);
        return result;
    }

}
