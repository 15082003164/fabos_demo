package com.cxxy.bysj.service.impl;

import com.cxxy.bysj.dao.KindsMapper;
import com.cxxy.bysj.entity.Kinds;
import com.cxxy.bysj.service.KindsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("KindsService")
public class KindsServiceImpl implements KindsService {

    @Autowired(required = false)
    KindsMapper kindsMapper;

    @Override
    public List<Kinds> selectKindById() {
        return kindsMapper.selectKindById();
    }

    @Override
    public int insertKind(Kinds kinds) {
        return kindsMapper.insertKind(kinds);
    }

    @Override
    public int updateKind(Kinds kinds) {
        return kindsMapper.updateKind(kinds);
    }

    @Override
    public int deleteKindById(Integer kinds_id) {
        return kindsMapper.deleteKindById(kinds_id);
    }
}
