package com.cxxy.bysj.controller.front;


import com.cxxy.bysj.entity.*;
import com.cxxy.bysj.service.CateService;
import com.cxxy.bysj.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private CateService cateService;

    @Autowired
    private GoodsService goodsService;


    @RequestMapping("/")
    public String showAdmin(Model model, HttpSession session) {
        Integer userid;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            userid = null;
        } else {
            userid = user.getUserid();
        }
        //软欧
        List<Goods> roGoods = getCateGoods("软欧", userid);
        model.addAttribute("roGoods", roGoods);
        //法式
        List<Goods> fsGoods = getCateGoods("法式", userid);
        model.addAttribute("fsGoods", fsGoods);
        //花式
        List<Goods> hsGoods = getCateGoods("花式", userid);
        model.addAttribute("hsGoods", hsGoods);
        //中式
        List<Goods> zsGoods = getCateGoods("中式", userid);
        model.addAttribute("zsGoods", zsGoods);
        //美式
        List<Goods> msGoods = getCateGoods("美式", userid);
        model.addAttribute("msGoods", msGoods);
        //法国
        List<Goods> fgGoods = getCateGoods("法国", userid);
        model.addAttribute("fgGoods", fgGoods);
        //日式
        List<Goods> rsGoods = getCateGoods("日式", userid);
        model.addAttribute("rsGoods", rsGoods);

        return "main";
    }




    @RequestMapping("/main")
    public String showAllGoods(Model model, HttpSession session) {
        Integer userid;
        User user = (User) session.getAttribute("user");
        if (user == null) {
            userid = null;
        } else {
            userid = user.getUserid();
        }
        //软欧
        List<Goods> roGoods = getCateGoods("软欧", userid);
        model.addAttribute("roGoods", roGoods);
        //法式
        List<Goods> fsGoods = getCateGoods("法式", userid);
        model.addAttribute("fsGoods", fsGoods);
        //花式
        List<Goods> hsGoods = getCateGoods("花式", userid);
        model.addAttribute("hsGoods", hsGoods);
        //中式
        List<Goods> zsGoods = getCateGoods("中式", userid);
        model.addAttribute("zsGoods", zsGoods);
        //美式
        List<Goods> msGoods = getCateGoods("美式", userid);
        model.addAttribute("msGoods", msGoods);
        //法国
        List<Goods> fgGoods = getCateGoods("法国", userid);
        model.addAttribute("fgGoods", fgGoods);
        //日式
        List<Goods> rsGoods = getCateGoods("日式", userid);
        model.addAttribute("rsGoods", rsGoods);

        return "main";
    }

    public List<Goods> getCateGoods(String cate, Integer userid) {
        //查询分类
        CategoryExample digCategoryExample = new CategoryExample();
        digCategoryExample.or().andCatenameLike(cate);
        List<Category> digCategoryList = cateService.selectByExample(digCategoryExample);

        if (digCategoryList.size() == 0) {
            return null;
        }

        //查询属于刚查到的分类的商品
        GoodsExample digGoodsExample = new GoodsExample();
        List<Integer> digCateId = new ArrayList<Integer>();
        for (Category tmp:digCategoryList) {
            digCateId.add(tmp.getCateid());
        }
        digGoodsExample.or().andCategoryIn(digCateId);

        List<Goods> goodsList = goodsService.selectByExampleLimit(digGoodsExample);

        List<Goods> goodsAndImage = new ArrayList<>();
        //获取每个商品的图片
        for (Goods goods:goodsList) {
            //判断是否为登录状态

            List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());
            goods.setImagePaths(imagePathList);
            goodsAndImage.add(goods);
        }
        return goodsAndImage;
    }
}
