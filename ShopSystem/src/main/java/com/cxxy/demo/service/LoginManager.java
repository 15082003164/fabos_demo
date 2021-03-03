package com.cxxy.demo.service;

import com.cxxy.demo.bean.User;
import com.cxxy.demo.dao.IUserDao;
import com.cxxy.demo.util.Md5;
import com.cxxy.demo.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class LoginManager {

    @Resource
    public IUserDao userDao;

    public IUserDao getUserDao() {
        return userDao;
    }

    public void setUserDao(IUserDao userDao) {
        this.userDao = userDao;
    }

    /**
     * @Title: listManagers
     * @Description: 查询用户集合
     * @param manager
     * @return List<Picnews>
     */
    public List<User> listUsers(User manager){
        List<User> managers = userDao.listUsers(manager);
        return managers;
    }

    /**
     * @Title: getManager
     * @Description: 查询用户
     * @param manager
     * @return Manager
     */
    public User getUser(User manager){

        if (!StringUtil.isEmpty(manager.getUser_pass())) {
            manager.setUser_pass(Md5.makeMd5(manager.getUser_pass()));
        }
        User _manager = userDao.getUser(manager);


        return _manager;
    }

    /**
     * @Title: addManager
     * @Description: 用户注册
     * @return void
     */
    public void addUser(User manager) {

        if (!StringUtil.isEmpty(manager.getUser_pass())) {
            manager.setUser_pass(Md5.makeMd5(manager.getUser_pass()));
        }
        userDao.addUser(manager);
    }
}
