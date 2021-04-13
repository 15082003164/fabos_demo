package com.cxxy.bysj.service;

import com.cxxy.bysj.entity.Kinds;


import java.util.List;

public interface KindsService {

    public List<Kinds> selectKindById();

    public int insertKind(Kinds kinds);

    public int updateKind(Kinds kinds);

    public int deleteKindById(Integer kinds_id);
}
