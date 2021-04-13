package com.cxxy.bysj.controller.admin;

import com.cxxy.bysj.entity.*;
import com.cxxy.bysj.service.KindsService;
import com.cxxy.bysj.util.Msg;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin/kinds")
public class KindsController {

    @Autowired
    private KindsService kindsService;

    @RequestMapping("/showjson")
    @ResponseBody
    public Msg getAllKinds(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
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

    @RequestMapping("/show")
    public String kindsManage(@RequestParam(value = "page", defaultValue = "1") Integer pn, HttpServletResponse response, Model model, HttpSession session) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        List<Kinds> kindsList = kindsService.selectKindById();
        model.addAttribute("kindsList", kindsList);

        return "adminAllKinds";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Msg updateKinds(Kinds kinds, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
        kindsService.updateKind(kinds);
        return Msg.success("更新成功!");
    }

    @RequestMapping(value = "/delete/{kinds_id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteKinds(@PathVariable("kinds_id") Integer kinds_id, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return Msg.fail("请先登录");
        }
        kindsService.deleteKindById(kinds_id);
        return Msg.success("删除成功!");
    }

    @RequestMapping("/addSuccess")
//    @ResponseBody
    public String addKinds(Kinds kinds, HttpSession session,RedirectAttributes redirectAttributes) throws IOException {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        kinds.setKinds_id(kinds.getKinds_id());
        kinds.setKinds_name(kinds.getKinds_name());
        kinds.setKinds_num(kinds.getKinds_num());
        kindsService.insertKind(kinds);
        redirectAttributes.addFlashAttribute("succeseMsg", "材料添加成功!");

        return "redirect:/admin/kinds/add";
    }

    @RequestMapping("/add")
    public String showAdd(@ModelAttribute("succeseMsg") String msg, Model model, HttpSession session) {
        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        if (!msg.equals("")) {
            model.addAttribute("msg", msg);
        }

        List<Kinds> kindsList = kindsService.selectKindById();
        model.addAttribute("kindsList", kindsList);

        //还需要查询分类传给addKinds页面
        return "addKinds";
    }
}
