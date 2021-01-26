package com.yangy.springboot.service;

import com.yangy.springboot.dao.UserRoleDao;
import com.yangy.springboot.model.User;
import com.yangy.springboot.model.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

@Slf4j
@Service
@Transactional
public class BondUnbondUserRoleService {

    @Resource
    private UserRoleDao userRoleDao;

    public int bond(UserRole userRole) {
        userRole.setUserId(userRole.getUserId());
        userRole.setRoleId(userRole.getRoleId());
        int result = userRoleDao.insertSelective(userRole);
        return result;
    }

    }
//    public void bond(UserRole userRole) {
//        userRoleDao.bondUserAndRole(userRole);
//    }
//
//    public void unbond(UserRole userRole) {
//        userRoleDao.unbondUserAndRole(userRole);
//    }



//        int userRoleBond = 1;
//
//
//        for (Integer i : userRoleBondDTO.getUserId()) {
//            UserRole userRole = new UserRole(null, null);
//            BeanUtils.copyProperties(i, userRole);
//            userRole.setUserId(userRole.getUserId());
//            userRole.setRoleId(userRole.getRoleId());
//
//            userRoleBond = userRoleBondDao.userRoleBond(userRole);
//            if (userRoleBond != 1) break;
//            log.info("create new userRoleBond : {}.", i);
//        }
//
//        return CheckResult.checkUpdateForInteger(userRoleBond);
