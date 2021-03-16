package com.cxxy.bysj.service;


import com.cxxy.bysj.entity.Retail;
import com.cxxy.bysj.entity.RetailConfig;

import java.util.List;

public interface RetailService {

    public List<RetailConfig> selectConfig();

    public void updateConfig(RetailConfig retailConfig);

    public void insertRetail(Retail retail);

    public void updateRetailById(Retail retail,String username);

    public List<Retail> selectRetailByUserId(String username);

    public Double selectTotalPriceByUsername(String username);
}
