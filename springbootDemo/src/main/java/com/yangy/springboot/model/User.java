package com.yangy.springboot.model;

//import lombok.Data;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;
//
//@Data
//@Entity
//@Table(name = "user")
//public class User {
//    @Id
//    @Column(name = "id")
//    private Integer id;
//
//    @Column(name = "username")
//    private String userName;
//
//    @Column(name = "password")
//    private String passWord;
//}


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Integer id;
    private String username;
    private String password;
}
