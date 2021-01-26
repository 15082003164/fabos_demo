package com.yangy.springboot.service;

import com.yangy.springboot.dao.UserPurviewDao;
import com.yangy.springboot.model.Purview;
import com.yangy.springboot.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserPurviewService {

    @Resource
    private UserPurviewDao userPurviewDao;



    public List<String> getPurviewIdByUser(User user){
        return userPurviewDao.getPurviewIdByUser(user.getUsername());
    }

    public List<String> getUserByPurviewId(Purview purview){
        return userPurviewDao.getUserByPurviewId(purview.getPurviewId());
    }

}
