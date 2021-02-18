package com.cxxy.demo.bean;

import com.cxxy.demo.util.BaseDomain;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "role")
public class User extends BaseDomain {
    private static final long serialVersionUID = -460922993085630428L;

    private String username;
    private String password;
    private int user_type;  //1:普通用户 2:分销商 3:管理员

}
