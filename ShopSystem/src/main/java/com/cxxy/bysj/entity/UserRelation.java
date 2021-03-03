package com.cxxy.bysj.entity;

public class UserRelation {
    private Integer id;
    private String user_parent_id;//父级用户id
    private String user_children_id;//子级用户id
    private Integer user_level;//用户等级

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUser_parent_id() {
        return user_parent_id;
    }

    public void setUser_parent_id(String user_parent_id) {
        this.user_parent_id = user_parent_id == "" ? "-" : user_parent_id;
    }

    public String getUser_children_id() {
        return user_children_id;
    }

    public void setUser_children_id(String user_children_id) {
        this.user_children_id = user_children_id;
    }

    public Integer getUser_level() {
        return user_level;
    }

    public void setUser_level(Integer user_level) {
        this.user_level = user_level;
    }

    @Override
    public String toString() {
        return "UserRelation{" +
                "id=" + id +
                ", user_parent_id=" + user_parent_id +
                ", user_children_id=" + user_children_id +
                ", user_level=" + user_level +
                '}';
    }
}
