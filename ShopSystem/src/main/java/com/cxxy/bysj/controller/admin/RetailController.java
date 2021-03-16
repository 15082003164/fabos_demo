package com.cxxy.bysj.controller.admin;

import com.cxxy.bysj.entity.Admin;
import com.cxxy.bysj.entity.RetailConfig;
import com.cxxy.bysj.service.RetailService;
import com.cxxy.bysj.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/admin/retail")
public class RetailController {
    @Autowired
    RetailService retailService;

    @RequestMapping("/show")
    public String showRetail(@RequestParam(value = "page",defaultValue = "1") Integer pn, Model model, HttpSession session) {

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        //一页显示几个数据
        PageHelper.startPage(pn, 10);



        List<RetailConfig> retailConfigList = retailService.selectConfig();

        //显示几个页号
        PageInfo page = new PageInfo(retailConfigList,5);
        model.addAttribute("pageInfo", page);

        return "retail";
    }

    @RequestMapping("/saveConfig")
    @ResponseBody
    public Msg saveConfig(Integer retail_first_percent, Integer retail_second_percent, Integer retail_third_percent, Double price_config, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");

        if (admin == null) {
            return Msg.fail("请先登录");
        }

        RetailConfig retailConfig = new RetailConfig();
        retailConfig.setRetail_first_percent(retail_first_percent);
        retailConfig.setRetail_second_percent(retail_second_percent);
        retailConfig.setRetail_third_percent(retail_third_percent);
        retailConfig.setPrice_config(price_config);

        retailService.updateConfig(retailConfig);
        return Msg.success("更新成功");
    }

//    @RequestMapping("/visitPrice")
//    public String showRetailPrice(@RequestParam(value = "page",defaultValue = "1") Integer pn, Model model, HttpSession session) {
//
//        Admin admin = (Admin) session.getAttribute("admin");
//        if (admin == null) {
//            return "redirect:/admin/login";
//        }
//
//        //一页显示几个数据
//        PageHelper.startPage(pn, 10);
//
//
//
//        List<RetailConfig> retailConfigList = retailService.selectConfig();
//
//        //显示几个页号
//        PageInfo page = new PageInfo(retailConfigList,5);
//        model.addAttribute("pageInfo", page);
//
//        return "";
//    }
//
}
