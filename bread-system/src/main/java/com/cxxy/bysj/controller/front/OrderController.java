package com.cxxy.bysj.controller.front;


import com.cxxy.bysj.entity.*;
import com.cxxy.bysj.service.*;
import com.cxxy.bysj.util.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.cxxy.bysj.util.OperationUtil.division;


@Controller
public class OrderController {


    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private SaleService saleService;

    @RequestMapping("/order")
    public String showOrder(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }


        //订单信息
        //获取当前用户的购物车信息
        ShopCartExample shopCartExample = new ShopCartExample();
        shopCartExample.or().andUseridEqualTo(user.getUserid());
        List<ShopCart> shopCart = shopCartService.selectByExample(shopCartExample);

        //获取购物车中的商品信息
        List<Goods> goodsAndImage = new ArrayList<>();

        Float totalPrice = new Float(0);
        Integer oldTotalPrice = 0;

        for (ShopCart cart : shopCart) {
            //分别从购物车列表中获取每个商品
            Goods goods = goodsService.selectById(cart.getGoodsid());

            List<ImagePath> imagePathList = goodsService.findImagePath(goods.getGoodsid());
            goods.setImagePaths(imagePathList);
            goods.setNum(cart.getGoodsnum());

            //活动信息
            Activity activity = activityService.selectByKey(goods.getActivityid());
            goods.setActivity(activity);

            //处理折扣信息
            //如果商品折扣不为1
            if (activity.getDiscount() != 1) {
                goods.setNewPrice(goods.getPrice() * goods.getNum() * activity.getDiscount()/10);
                System.out.println("价格为：" + goods.getPrice() * goods.getNum() * (activity.getDiscount())/10);
            } else if (activity.getFullnum() != null) {
                System.out.println("进入第二层方法");
                if (goods.getNum() >= activity.getFullnum()) {
                    goods.setNewPrice((float) (goods.getPrice() * (goods.getNum() - activity.getReducenum())));
                } else {
                    goods.setNewPrice((float) (goods.getPrice() * goods.getNum()));
                }
            } else if (activity.getFullprice() != null && activity.getReducenum() != null) {
                if ((goods.getNum() * goods.getNum()) > activity.getFullprice()) {
                    goods.setNewPrice((float) (goods.getPrice() * goods.getNum() - activity.getReducenum()));
                } else {
                    goods.setNewPrice((float) (goods.getPrice() * goods.getNum()));

                }

            } else {
                goods.setNewPrice((float) (goods.getPrice() * goods.getNum()));
            }
            totalPrice = totalPrice + goods.getNewPrice();
            oldTotalPrice = oldTotalPrice + goods.getNum() * goods.getPrice();
            goodsAndImage.add(goods);

        }

        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("oldTotalPrice", oldTotalPrice);
        model.addAttribute("goodsAndImage", goodsAndImage);

        return "orderConfirm";
    }

    @RequestMapping("/orderFinish")
    @ResponseBody
    public Msg orderFinish(Float oldPrice, Float newPrice, Boolean isPay, HttpSession session ,Model model) {
        User user = (User) session.getAttribute("user");

        //获取订单信息
        ShopCartExample shopCartExample = new ShopCartExample();
        shopCartExample.or().andUseridEqualTo(user.getUserid());
        List<ShopCart> shopCart = shopCartService.selectByExample(shopCartExample);

        //把订单信息写入数据库
        Order order = new Order(null, user.getUserid(), new Date(), oldPrice, newPrice, isPay, false, false, false, null);
        orderService.insertOrder(order);
        //插入的订单号
        Integer orderId = order.getOrderid();



        //把订单项写入orderitem表,修改商品数量
        for (ShopCart cart : shopCart) {
            orderService.insertOrderItem(new OrderItem(null, orderId, cart.getGoodsid(), cart.getGoodsnum()));
            Goods goods = goodsService.selectById(cart.getGoodsid());
            goodsService.updateNumForOrderFinish(goods.getGoodsid(),goods.getNum() - cart.getGoodsnum());
            Sale sale = new Sale(goods.getGoodsid(),goods.getGoodsname(),cart.getGoodsnum(),order.getOrdertime());
            saleService.insertByGoodsId(sale);
        }

        //删除购物车
        for (ShopCart cart : shopCart) {
            shopCartService.deleteByKey(new ShopCartKey(cart.getUserid(), cart.getGoodsid()));
        }

        return Msg.success("购买成功");

    }
}

