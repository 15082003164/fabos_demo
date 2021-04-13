package com.cxxy.bysj.controller.front;


import com.cxxy.bysj.service.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cxxy.bysj.entity.*;
import com.cxxy.bysj.util.Md5Util;
import com.cxxy.bysj.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@Controller
public class CustomerController {

    @RequestMapping("/login")
    public String loginView() {
        return "login";
    }

    @Autowired
    private UserService userService;


    @RequestMapping("/register")
    public String register() {
        return "register";
    }

    @RequestMapping("/registerresult")
    public String registerResult(User user, Model registerResult) {
        List<User> userList = new ArrayList<>();
        user.setPassword(Md5Util.MD5Encode(user.getPassword(), "utf-8"));
        UserExample userExample = new UserExample();
        userExample.or().andUsernameLike(user.getUsername());
        userList = userService.selectByExample(userExample);
        if (!userList.isEmpty()) {
            registerResult.addAttribute("errorMsg", "用户名被占用");
            return "register";
        } else {
                Date RegTime = new Date();
                user.setRegtime(RegTime);
                userService.insertSelective(user);
                return "redirect:/login";
            }

        }


    @RequestMapping("/loginconfirm")
    public String loginConfirm(User user, Model loginResult, HttpServletRequest request, @RequestParam("confirmlogo") String confirmlogo) {
        System.out.println("传进来的用户帐号和密码为:" + user);
        //进行用户密码MD5加密验证
        user.setPassword(Md5Util.MD5Encode(user.getPassword(), "UTF-8"));
        HttpSession session = request.getSession();
        String verificationCode = (String) session.getAttribute("certCode");
        if (!confirmlogo.equals(verificationCode)) {
            loginResult.addAttribute("errorMsg", "验证码错误");
            return "login";

        }
        List<User> userList = new ArrayList<User>();
        UserExample userExample = new UserExample();
        userExample.or().andUsernameEqualTo(user.getUsername()).andPasswordEqualTo(user.getPassword());
        userList = userService.selectByExample(userExample);
        if (!userList.isEmpty()) {
            session.setAttribute("user", userList.get(0));
            return "redirect:/main";
        } else {
            loginResult.addAttribute("errorMsg", "用户名与密码不匹配");
            return "login";
        }
    }

    @RequestMapping("/information")
    public String information(Model userModel, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user;
        Integer userId;
        user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        userId = user.getUserid();
        user = userService.selectByPrimaryKey(userId);
        userModel.addAttribute("user", user);
        return "information";
    }

    @RequestMapping("/saveInfo")
    @ResponseBody
    public Msg saveInfo(String name, String email, String telephone, HttpServletRequest request) {
        HttpSession session = request.getSession();
        UserExample userExample = new UserExample();
        User user, updateUser = new User();
        List<User> userList = new ArrayList<>();
        Integer userid;
        user = (User) session.getAttribute("user");
        userid = user.getUserid();
        userExample.or().andUsernameEqualTo(name);
        userList = userService.selectByExample(userExample);
        if (userList.isEmpty()) {
            updateUser.setUserid(userid);
            updateUser.setUsername(name);
            updateUser.setEmail(email);
            updateUser.setTelephone(telephone);
            userService.updateByPrimaryKeySelective(updateUser);
            return Msg.success("更新成功");
        } else {
            return Msg.fail("更新失败");
        }
    }

    @Autowired
    private AddressService addressService;

    @RequestMapping("/info/address")
    public String address(HttpServletRequest request, Model addressModel) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }
        AddressExample addressExample = new AddressExample();
        addressExample.or().andUseridEqualTo(user.getUserid());
        List<Address> addressList = addressService.getAllAddressByExample(addressExample);
        addressModel.addAttribute("addressList", addressList);
        return "address";
    }

//    @RequestMapping("/info/retail")
//    public String retail(HttpServletRequest request, Model retailModel) {
//        HttpSession session = request.getSession();
//        User user = (User) session.getAttribute("user");
//        if (user == null) {
//            return "redirect:/login";
//        }
//        List<Retail> retailList = retailService.selectRetailByUserId(user.getUsername());
//        retailModel.addAttribute("retailList", retailList);
//        return "myretail";
//    }


    @RequestMapping("/saveAddr")
    @ResponseBody
    public Msg saveAddr(Address address) {

        addressService.updateByPrimaryKeySelective(address);
        return Msg.success("修改成功");
    }

    @RequestMapping("/deleteAddr")
    @ResponseBody
    public Msg deleteAddr(Address address) {
        addressService.deleteByPrimaryKey(address.getAddressid());
        return Msg.success("删除成功");
    }

    @RequestMapping("/insertAddr")
    @ResponseBody
    public Msg insertAddr(Address address, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = new User();
        user = (User) session.getAttribute("user");
        address.setUserid(user.getUserid());
        addressService.insertSelective(address);
        return Msg.success("添加成功");
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("/info/list")
    public String list(HttpServletRequest request, Model orderModel) {

        HttpSession session = request.getSession();
        User user;
        user = (User) session.getAttribute("user");

        if (user == null) {
            return "redirect:/login";
        }

        OrderExample orderExample = new OrderExample();
        orderExample.or().andUseridEqualTo(user.getUserid());

        List<Order> orderList = orderService.selectOrderByExample(orderExample);
        orderModel.addAttribute("orderList", orderList);
        Order order;
        OrderItem orderItem;
        List<OrderItem> orderItemList = new ArrayList<>();
        Goods goods = new Goods();
        Address address;
        for (Integer i = 0; i < orderList.size(); i++) {
            order = orderList.get(i);
            OrderItemExample orderItemExample = new OrderItemExample();
            orderItemExample.or().andOrderidEqualTo(order.getOrderid());
            orderItemList = orderService.getOrderItemByExample(orderItemExample);
//            List<Goods> goodsList = new ArrayList<>();
//            List<Integer> goodsIdList = new ArrayList<>();
//            Map<Integer, Integer> map = new HashMap<>();
            List<Goods> listGood = new ArrayList<>();

            for (Integer j = 0; j < orderItemList.size(); j++) {
                orderItem = orderItemList.get(j);
//                goodsIdList.add(orderItem.getGoodsid());
//                map.put(orderItem.getGoodsid(),orderItem.getNum());
                goods = goodsService.selectById(orderItem.getGoodsid());
                goods.setGoods_num(orderItem.getNum());
                listGood.add(goods);
            }

//            for(Integer id : map.keySet()){
//                goods = goodsService.selectById(id);
//                goods.setGoods_num(map.get(id));
//                listGood.add(goods);
//            }
            order.setGoodsInfo(listGood);
            address = addressService.selectByPrimaryKey(order.getAddressid());
            order.setAddress(address);
            orderList.set(i, order);
        }
        orderModel.addAttribute("orderList", orderList);

        return "list";
    }


    @RequestMapping("/deleteList")
    @ResponseBody
    public Msg deleteList(Order order) {
        orderService.deleteById(order.getOrderid());
        return Msg.success("删除成功");
    }


    @RequestMapping("/savePsw")
    @ResponseBody
    public Msg savePsw(String Psw, HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        user.setPassword(Md5Util.MD5Encode(Psw, "UTF-8"));
        userService.updateByPrimaryKeySelective(user);
        return Msg.success("修改密码成功");
    }

    @RequestMapping("/finishList")
    @ResponseBody
    public Msg finishiList(Integer orderid) {
        Order order = orderService.selectByPrimaryKey(orderid);
        order.setIsreceive(true);
        order.setIscomplete(true);
        orderService.updateOrderByKey(order);
        return Msg.success("完成订单成功");
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        return "redirect:/login";
    }

}