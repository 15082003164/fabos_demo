package com.yangy.practice.Service;

import com.yangy.practice.Mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public Object updateUser(){
        userMapper
    }
}
