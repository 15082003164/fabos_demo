package com.cxxy.bysj.service;


import com.cxxy.bysj.entity.User;
import com.cxxy.bysj.entity.UserExample;
import com.cxxy.bysj.entity.UserRelation;

import java.util.List;

public interface UserService {
    public User selectByPrimaryKey(int userId);
    /*public User selectByPrimaryKeyAndPassword(int userId,String password);*/
    public List<User> selectByExample(UserExample userExample);

    public void insertSelective(User user);

    public void deleteUserById(Integer userid);

    public void updateByPrimaryKeySelective(User user);

    public void insertSelectiveForRelation(UserRelation userRelation);

    public List<String> getUsernameBySelect();

    public int CountByUserChildrenId(String user_children_id);

    public String getUserParentIdByUserName(String username);

    public void deleteUserRelationByChild(String user_children_id);

    public List<UserRelation> SelectUserRelationByUserId(Integer userid);

}
