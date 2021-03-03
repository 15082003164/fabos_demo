package com.cxxy.demo.controller;

import com.cxxy.demo.bean.User;
import com.cxxy.demo.service.LoginManager;
import com.cxxy.demo.util.JSONData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    LoginManager loginManager;
//    public LoginManager getLoginManager() {
//        return loginManager;
//    }
//    public void setLoginManager(LoginManager loginManager) {
//        this.loginManager = loginManager;
//    }

    /**
     * @Title: InSystem
     * @Description: 用户登录
     * @return String
     */
    @RequestMapping(value="admin/LoginInSystem.action",method=RequestMethod.POST)
    public String InSystem(User params,
                           ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
        try {
            String random = (String)httpSession.getAttribute("random");
            if (!random.equals(params.getRandom())) {
                model.addAttribute("tip","验证码错误");
                model.addAttribute("params",params);
                return "login";
            }

            //用户登录查询
            //params.setUser_type(4);
            User admin = loginManager.getUser(params);
            if (admin!=null) {
                httpSession.setAttribute("admin", admin);
            }else {
                model.addAttribute("tip","用户名或密码错误");
                model.addAttribute("params",params);
                return "login";
            }

        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("tip","登录异常，请稍后重试");
            model.addAttribute("params",params);
            return "login";
        }

        return "redirect:index.jsp";
    }

    /**
     * @Title: OutSystem
     * @Description: 退出登录
     * @return String
     */
    @RequestMapping(value="admin/LoginOutSystem.action",method=RequestMethod.GET)
    public String OutSystem(HttpSession httpSession){
        try {
            //用户查询
            User user = (User)httpSession.getAttribute("admin");
            if (user!=null) {
                //退出登录
                httpSession.removeAttribute("admin");
                httpSession.invalidate();
            }

        } catch (Exception e) {
            return "login";
        }

        return "login";
    }

    /**
     * @Title: RegSystem
     * @Description: 用户注册
     * @return String
     */
    @RequestMapping(value="LoginRegSystem.action",method=RequestMethod.POST)
    @ResponseBody
    public JSONData RegSystem(User params,
                              ModelMap model,HttpServletRequest request,HttpServletResponse response,HttpSession httpSession){
        JSONData jsonData = new JSONData();
        try {
            //验证码验证
            String random = (String)httpSession.getAttribute("random");
            if (!random.equals(params.getRandom())) {
                jsonData.setErrorReason("验证码错误");
                return jsonData;
            }

            //查询用户名是否被占用
            User user = new User();
            user.setUser_name(params.getUser_name());
            User user_temp = loginManager.getUser(user);
            if (user_temp!=null) {
                jsonData.setErrorReason("注册失败，用户名已被注册："+params.getUser_name());
                return jsonData;
            }

            //添加用户入库
            loginManager.addUser(params);

        } catch (Exception e) {
            jsonData.setErrorReason("注册异常，请稍后重试");
            return jsonData;
        }

        return jsonData;
    }

}
