package com.cxxy.bysj.service.impl;

import com.cxxy.bysj.dao.RetailMapper;
import com.cxxy.bysj.entity.Retail;
import com.cxxy.bysj.entity.RetailConfig;
import com.cxxy.bysj.service.RetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("retailService")
public class RetailServiceImpl implements RetailService {

    @Autowired(required = false)
    RetailMapper retailMapper;

    @Override
    public List<RetailConfig> selectConfig() {
        return retailMapper.selectConfig();
    }

    @Override
    public void updateConfig(RetailConfig retailConfig) {
        retailMapper.updateConfig(retailConfig);
    }

    @Override
    public void insertRetail(Retail retail) {
        retailMapper.insertRetail(retail);
    }

    @Override
    public void updateRetailById(Retail retail,String username) {
        retailMapper.updateRetailById(retail,username);
    }

    @Override
    public List<Retail> selectRetailByUserId(String username) {
        return retailMapper.selectRetailByUserId(username);
    }

    @Override
    public Double selectTotalPriceByUsername(String username) {
        return retailMapper.selectTotalPriceByUsername(username);
    }
}
