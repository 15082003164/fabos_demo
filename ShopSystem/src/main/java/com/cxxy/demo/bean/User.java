package com.cxxy.demo.bean;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "user")
public class User extends BaseDomain {
    private static final long serialVersionUID = -460922993085630428L;

    @Id
    private int user_id;

    private String user_name;
    private String user_pass;
    private int user_type;  //1:普通用户 2:分销商 3:管理员
    private String random;
    private String name;
    private String user_phone;

}
