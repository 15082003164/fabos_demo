package com.cxxy.bysj.controller.cooker;


import com.cxxy.bysj.entity.*;
import com.cxxy.bysj.service.CateService;
import com.cxxy.bysj.service.CookerService;
import com.cxxy.bysj.service.GoodsService;
import com.cxxy.bysj.service.KindsService;
import com.cxxy.bysj.util.Md5Util;
import com.cxxy.bysj.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/cooker")
public class CookerController {

    @Autowired
    private CookerService cookerService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private CateService cateService;

    @Autowired
    private KindsService kindsService;


    @RequestMapping("/login")
    public String cookerLogin() {
        return "cookerLogin";
    }

    @RequestMapping("/confirmLogin")
    public String confirmLogin(Cooker cooker, Model model, HttpServletRequest request) {
        cooker.setPassword(Md5Util.MD5Encode(cooker.getPassword(),"utf-8"));
        Cooker selectCooker = cookerService.selectByName(cooker);
        if (selectCooker == null) {
            model.addAttribute("errorMsg", "用户名或密码错误");
            return "cookerLogin";
        } else {
            HttpSession session = request.getSession();
            session.setAttribute("cooker", selectCooker);
            return "redirect:/cooker/show";
        }
    }

    @RequestMapping("/logout")
    public String cookerLogout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.removeAttribute("cooker");
        return "redirect:/cooker/login";
    }


    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Msg updateGoods(Goods goods, HttpSession session) {
        Cooker cooker = (Cooker) session.getAttribute("cooker");
        if (cooker == null) {
            return Msg.fail("请先登录");
        }
        /* goods.setGoodsid(goodsid);*/
        goodsService.updateGoodsById(goods);
        return Msg.success("更新成功!");
    }

    //面包
    @RequestMapping("/showjson")
    @ResponseBody
    public Msg getAllGoods(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) {
        Cooker cooker = (Cooker) session.getAttribute("cooker");
        if (cooker == null) {
            return Msg.fail("请先登录");
        }
        //一页显示几个数据
        PageHelper.startPage(pn, 10);

        List<Goods> goodsList = goodsService.selectByExample(new GoodsExample());
        for(Goods good:goodsList){
            System.out.println(good);
        }
        //显示几个页号
        PageInfo page = new PageInfo(goodsList, 5);

        model.addAttribute("pageInfo", page);

        return Msg.success("查询成功!").add("pageInfo", page);
    }

    @RequestMapping("/show")
    public String goodsManage(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        Cooker cooker = (Cooker) session.getAttribute("cooker");
        if (cooker == null) {
            return "redirect:/cooker/login";
        }

        List<Category> categoryList = cateService.selectByExample(new CategoryExample());
        model.addAttribute("categoryList", categoryList);

        return "cookerAllGoods";
    }

    //材料
    @RequestMapping("/kinds/showjson")
    @ResponseBody
    public Msg getAllKinds(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) {
        Cooker cooker = (Cooker) session.getAttribute("cooker");
        if (cooker == null) {
            return Msg.fail("请先登录");
        }
        //一页显示几个数据
        PageHelper.startPage(pn, 10);

        List<Kinds> kindsList = kindsService.selectKindById();
        for(Kinds kinds:kindsList){
            System.out.println(kinds);
        }
        //显示几个页号
        PageInfo page = new PageInfo(kindsList, 5);

        model.addAttribute("pageInfo", page);

        return Msg.success("查询成功!").add("pageInfo", page);
    }

    @RequestMapping("/kinds/show")
    public String kindsManage(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        Cooker cooker = (Cooker) session.getAttribute("cooker");
        if (cooker == null) {
            return "redirect:/cooker/login";
        }

        List<Kinds> kindsList = kindsService.selectKindById();
        model.addAttribute("kindsList", kindsList);

        return "cookerAllKinds";
    }

    @RequestMapping(value = "/kinds/update", method = RequestMethod.POST)
    @ResponseBody
    public Msg updateKinds(Kinds kinds, HttpSession session) {
        Cooker cooker = (Cooker) session.getAttribute("cooker");
        if (cooker == null) {
            return Msg.fail("请先登录");
        }
        kindsService.updateKind(kinds);
        return Msg.success("更新成功!");
    }
}
