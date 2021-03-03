package com.cxxy.bysj.service;


import com.cxxy.bysj.entity.RetailConfig;

import java.util.List;

public interface RetailService {

    public List<RetailConfig> selectConfig();

    public void updateConfig(RetailConfig retailConfig);
}
