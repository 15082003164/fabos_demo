package com.cxxy.bysj.service.impl;


import com.cxxy.bysj.dao.ActivityMapper;
import com.cxxy.bysj.entity.Activity;
import com.cxxy.bysj.entity.ActivityExample;
import com.cxxy.bysj.service.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("activityService")
public class ActivityServiceImpl implements ActivityService {

    @Autowired(required = false)
    ActivityMapper activityMapper;

    public List<Activity> getAllActivity(ActivityExample activityExample) {
        return activityMapper.selectByExample(activityExample);
    }

    @Override
    public void insertActivitySelective(Activity activity) {
        activityMapper.insertSelective(activity);
    }

    @Override
    public Activity selectByKey(Integer activityid) {
        return activityMapper.selectByPrimaryKey(activityid);
    }

    @Override
    public void deleteByActivityId(Integer activityid) {
        activityMapper.deleteByPrimaryKey(activityid);
    }

    /*@Override
    public void updateGoodsActSelective(Goods goods) {

    }*/
}
