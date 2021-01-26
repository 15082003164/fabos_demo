package com.yangy.springboot.service;

import com.yangy.springboot.dao.UserDao;
import com.yangy.springboot.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Service层调用Dao层的接口
 * @version 0.0.1
 * @author yangy
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    //用户查询
    public List<User> selectAll(){
        return userDao.selectAll();
    }

    //用户删除
    public void deleteUser(Integer id){
       userDao.deleteUser(id);

    }

    //用户新增
    public void insertUser(User user){
        userDao.insertUser(user);
    }

    //用户修改
    public void updateUser(User user){
        userDao.updateUser(user);
    }
}
