package com.yangy.springboot.controller;

import com.yangy.springboot.model.User;
import com.yangy.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

//    @Autowired
//    public JdbcTemplate jdbcTemplate;

    @Autowired
    public UserService userService;

//    @RequestMapping("/getUser")
//    public List<Map<String,Object>> getUser(){
//            String sql ="select * from student";
//            List<Map<String,Object>> list = jdbcTemplate.queryForList(sql);
//            return list;
//    }

    @RequestMapping("/selectAll")
    public List<User> selectAll() {
        return userService.selectAll();
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(Integer id) {
        userService.deleteUser(id);
        return "删除成功！";
    }

    @RequestMapping("/insertUser")
    public String insertUser(@ModelAttribute User user) {
        userService.insertUser(user);
        return "新增成功！";
    }

    @RequestMapping("/updateUser")
    public String updateUser(@ModelAttribute User user) {
        userService.updateUser(user);
        return "修改成功！";
    }

}
