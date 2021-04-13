package com.cxxy.bysj.service.impl;


import com.cxxy.bysj.dao.AdminMapper;
import com.cxxy.bysj.entity.Admin;
import com.cxxy.bysj.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired(required = false)
    private AdminMapper adminMapper;

    @Override
    public Admin selectByName(Admin admin) {
        return adminMapper.selectByName(admin);
    }
}
