package com.cxxy.bysj.service;


import com.cxxy.bysj.entity.Activity;
import com.cxxy.bysj.entity.ActivityExample;

import java.util.List;

public interface ActivityService {
    List<Activity> getAllActivity(ActivityExample activityExample);

    void insertActivitySelective(Activity activity);

    Activity selectByKey(Integer activityid);

    void deleteByActivityId(Integer activityid);

//    void updateGoodsActSelective(Goods goods);
}
