package com.cxxy.bysj.controller.admin;


import com.cxxy.bysj.service.SaleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.cxxy.bysj.entity.*;
import com.cxxy.bysj.service.GoodsService;
import com.cxxy.bysj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/admin/order")
public class AdminOrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private SaleService saleService;

    @RequestMapping("/send")
    public String sendOrder(@RequestParam(value = "page",defaultValue = "1")Integer pn, Model model, HttpSession session) {

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        //一页显示几个数据
        PageHelper.startPage(pn, 2);

        //查询订单
        OrderExample orderExample = new OrderExample();
        orderExample.or().andIssendEqualTo(false);
        List<Order> orderList = orderService.selectOrderByExample(orderExample);
        model.addAttribute("sendOrder", orderList);

        //查询该订单中的商品
        for (int i = 0; i < orderList.size(); i++) {
            //获取订单项中的goodsid
            Order order = orderList.get(i);
            OrderItemExample orderItemExample = new OrderItemExample();
            orderItemExample.or().andOrderidEqualTo(order.getOrderid());
            List<OrderItem> orderItemList = orderService.getOrderItemByExample(orderItemExample);
            List<Integer> goodsIdList = new ArrayList<>();

            List<Goods> goodsList = new ArrayList<>();
            for (OrderItem orderItem : orderItemList) {
                Goods goods = goodsService.selectById(orderItem.getGoodsid());
                goods.setNum(orderItem.getNum());
                goodsList.add(goods);
            }

            order.setGoodsInfo(goodsList);


            orderList.set(i, order);
        }

        //显示几个页号
        PageInfo page = new PageInfo(orderList,5);
        model.addAttribute("pageInfo", page);

        return "adminAllOrder";
    }


    @RequestMapping("/sale")
    public String saleManage(@RequestParam(value = "page",defaultValue = "1")Integer pn, Model model, HttpSession session) {

        Admin admin = (Admin) session.getAttribute("admin");
        if (admin == null) {
            return "redirect:/admin/login";
        }

        //一页显示几个数据
        PageHelper.startPage(pn, 10);

        List<Sale> saleList =saleService.selectAll();

        //显示几个页号
        PageInfo page = new PageInfo(saleList,5);
        model.addAttribute("pageInfo", page);

        return "adminSaleManage";
    }

}
