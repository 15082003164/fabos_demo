package com.cxxy.bysj.service.impl;

import com.cxxy.bysj.dao.CookerMapper;
import com.cxxy.bysj.entity.Cooker;
import com.cxxy.bysj.service.CookerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("cookerService")
public class CookerServiceImpl implements CookerService {

    @Autowired(required = false)
    CookerMapper cookerMapper;

    @Override
    public Cooker selectByName(Cooker cooker) {
        return cookerMapper.selectByName(cooker);
    }
}
