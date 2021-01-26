package com.yangy.springboot.controller;

import com.yangy.springboot.model.UserRole;
import com.yangy.springboot.service.BondUnbondUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BondUnbondUserRoleController {
    @Autowired
    private BondUnbondUserRoleService bondUnbondUserRoleService;

    @RequestMapping("/bondUserRole")
    public String bond(){
        int result = bondUnbondUserRoleService.bond();
        return result == 1 ? "下单成功" : "下单失败";
    }

//    @RequestMapping("/bondUserRole")
//    public String bond(UserRole userRole){
//        bondUnbondUserRoleService.bond(userRole);
//        return "绑定成功！";
//    }
//
//    @RequestMapping("/unbondUserRole")
//    public String unbond(UserRole userRole){
//        bondUnbondUserRoleService.unbond(userRole);
//        return "解绑成功！";
//    }
}
