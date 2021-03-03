package com.yangy.springboot.controller;

import com.yangy.springboot.model.Purview;
import com.yangy.springboot.model.User;
import com.yangy.springboot.service.UserPurviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SelectUserPurviewController {
    @Autowired
    private UserPurviewService userPurviewService;

    @RequestMapping("/getPurviewListByUser")
    public List<String> getPurviewListByUser(@RequestBody User user) {
        return userPurviewService.getPurviewIdByUser(user);
    }

    @RequestMapping("/getUserListByPurview")
    public List<String> getUserListByPurview(@RequestBody Purview purview) {
        return userPurviewService.getUserByPurviewId(purview);
    }

}
