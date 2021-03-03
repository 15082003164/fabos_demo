package com.cxxy.bysj.entity;

import java.util.Date;

public class User {
    private Integer userid;

    private String username;

    private String password;

    private Date regtime;

    private String email;

    private String telephone;

    private String user_parent_id;

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Date getRegtime() {
        return regtime;
    }

    public void setRegtime(Date regtime) {
        this.regtime = regtime;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public String getUser_parent_id() {
        return user_parent_id;
    }

    public void setUser_parent_id(String user_parent_id) {
        this.user_parent_id = user_parent_id == "" ? "-" : user_parent_id;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (this.getClass() != object.getClass())
            return false;
        final User user = (User) object;
        if (this.getUsername() != user.getUsername())
            return false;
        if (this.getUser_parent_id() != user.getUser_parent_id())
            return false;
        return true;
    }



    @Override
    public String toString() {
        return "User{" +
                "userid=" + userid +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", regtime=" + regtime +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                ", user_parent_id='" + user_parent_id + '\'' +
                '}';
    }
}