package com.cxxy.bysj.service.impl;


import com.cxxy.bysj.dao.UserMapper;
import com.cxxy.bysj.entity.User;
import com.cxxy.bysj.entity.UserExample;
import com.cxxy.bysj.entity.UserRelation;
import com.cxxy.bysj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired(required = false)
    private UserMapper userMapper;

    @Override
    public User selectByPrimaryKey(int userId) {
        return  userMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<User> selectByExample(UserExample userExample) {
        return userMapper.selectByExample(userExample);
    }

    @Override
    public void insertSelective(User user) {
        userMapper.insertSelective(user);
    }

    @Override
    public void deleteUserById(Integer userid) {
        userMapper.deleteByPrimaryKey(userid);
    }

    @Override
    public void updateByPrimaryKeySelective(User user) {
        userMapper.updateByPrimaryKeySelective(user);
    }

    @Override
    public void insertSelectiveForRelation(UserRelation userRelation) {
        userMapper.insertSelectiveForRelation(userRelation);
    }

    @Override
    public List<String> getUsernameBySelect() {
        return userMapper.getUsernameBySelect();
    }

    @Override
    public int CountByUserChildrenId(String user_children_id) {
        return userMapper.CountByUserChildrenId(user_children_id);
    }

    @Override
    public String getUserParentIdByUserName(String username) {
        return userMapper.getUserParentIdByUserName(username);
    }

    @Override
    public void deleteUserRelationByChild(String user_children_id) {
        userMapper.deleteUserRelationByChild(user_children_id);
    }


}
