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

    /*@Value("#{addressService}")*/
    @Autowired
    private AddressService addressService;

    @Autowired
    private ShopCartService shopCartService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private UserService userService;

    @Autowired
    private RetailService retailService;

    @RequestMapping("/order")
    public String showOrder(HttpSession session, Model model) {

        User user = (User) session.getAttribute("user");
        if (user == null) {
            return "redirect:/login";
        }

        //查询当前用户的收货地址
        AddressExample addressExample = new AddressExample();
        addressExample.or().andUseridEqualTo(user.getUserid());
        List<Address> addressList = addressService.getAllAddressByExample(addressExample);

        model.addAttribute("address", addressList);

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
                goods.setNewPrice(goods.getPrice() * goods.getNum() * activity.getDiscount());
                System.out.println("价格为：" + goods.getPrice() * goods.getNum() * activity.getDiscount());
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
    public Msg orderFinish(Float oldPrice, Float newPrice, Boolean isPay, Integer addressid, HttpSession session ,Model model) {
        User user = (User) session.getAttribute("user");
        Retail retail = new Retail();
        List<RetailConfig> retailConfigList = retailService.selectConfig();


        //获取订单信息
        ShopCartExample shopCartExample = new ShopCartExample();
        shopCartExample.or().andUseridEqualTo(user.getUserid());
        List<ShopCart> shopCart = shopCartService.selectByExample(shopCartExample);

        //删除购物车
        for (ShopCart cart : shopCart) {
            shopCartService.deleteByKey(new ShopCartKey(cart.getUserid(), cart.getGoodsid()));
        }

        //把订单信息写入数据库
        Order order = new Order(null, user.getUserid(), new Date(), oldPrice, newPrice, isPay, false, false, false, addressid, null, null);
        orderService.insertOrder(order);
        //插入的订单号
        Integer orderId = order.getOrderid();

        String str =user.getUser_parent_id();

        /**
         * 用户id(child) -> (根据user_level) -> parent_id(对应到user表中的userId) ->userId(Retail表中的userId) ->商品价格(indent表中的newPrice)*分销比 ->得到本次分销金额
         * -> total_cash_price = total_cash_price + 本次分销金额
         */


        //把订单项写入orderitem表和retail表中
        for (ShopCart cart : shopCart) {
            orderService.insertOrderItem(new OrderItem(null, orderId, cart.getGoodsid(), cart.getGoodsnum()));
            //分销信息
             RetailConfig retailConfig = retailConfigList.get(0);
            if (!str.equals("-")){
                List<UserRelation> list = userService.SelectUserRelationByUserId(user.getUserid());
                for(UserRelation userRelation : list){

                    if (retailService.selectRetailByUserId(userRelation.getUser_parent_id()).isEmpty()){
                        if (userRelation.getUser_level() == 1){

                            retail.setUsername(userRelation.getUser_parent_id());
                            retail.setThis_cash_price( (order.getNewprice() *  Double.parseDouble(division(retailConfig.getRetail_first_percent(),100))));
                            retail.setTotal_cash_price(retail.getThis_cash_price());
                            retailService.insertRetail(retail);
                        }
                        if (userRelation.getUser_level() == 2){
                            retail.setUsername(userRelation.getUser_parent_id());
                            retail.setThis_cash_price( (order.getNewprice() *  Double.parseDouble(division(retailConfig.getRetail_second_percent(),100))));
                            retail.setTotal_cash_price(retail.getThis_cash_price());
                            retailService.insertRetail(retail);
                        }
                        if (userRelation.getUser_level() == 3){
                            retail.setUsername(userRelation.getUser_parent_id());
                            retail.setThis_cash_price( (order.getNewprice() *  Double.parseDouble(division(retailConfig.getRetail_third_percent(),100))));
                            retail.setTotal_cash_price(retail.getThis_cash_price());
                            retailService.insertRetail(retail);
                        }
                    }else {
                        if (userRelation.getUser_level() == 1){
                            retail.setUsername(userRelation.getUser_parent_id());
                            retail.setThis_cash_price( (order.getNewprice() *  Double.parseDouble(division(retailConfig.getRetail_first_percent(),100))));
                            retail.setTotal_cash_price(retailService.selectTotalPriceByUsername(userRelation.getUser_parent_id()) + retail.getThis_cash_price());
                            retailService.updateRetailById(retail,userRelation.getUser_parent_id());
                        }
                        if (userRelation.getUser_level() == 2){
                            retail.setUsername(userRelation.getUser_parent_id());
                            retail.setThis_cash_price( (order.getNewprice() *  Double.parseDouble(division(retailConfig.getRetail_second_percent(),100))));
                            retail.setTotal_cash_price(retailService.selectTotalPriceByUsername(userRelation.getUser_parent_id()) + retail.getThis_cash_price());
                            retailService.updateRetailById(retail,userRelation.getUser_parent_id());
                        }
                        if (userRelation.getUser_level() == 3){
                            retail.setUsername(userRelation.getUser_parent_id());
                            retail.setThis_cash_price( (order.getNewprice() *  Double.parseDouble(division(retailConfig.getRetail_third_percent(),100))));
                            retail.setTotal_cash_price(retailService.selectTotalPriceByUsername(userRelation.getUser_parent_id()) + retail.getThis_cash_price());
                            retailService.updateRetailById(retail,userRelation.getUser_parent_id());
                        }
                    }

                }
//                Double this_cash_price = retail.getThis_cash_price();
//                Double total_cash_price = retail.getTotal_cash_price();
//
//                model.addAttribute("this_cash_price", this_cash_price);
//                model.addAttribute("total_cash_price", total_cash_price);
            }
        }

        return Msg.success("购买成功");
    }

}
