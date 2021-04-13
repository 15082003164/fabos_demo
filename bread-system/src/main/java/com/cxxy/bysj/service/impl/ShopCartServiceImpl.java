package com.cxxy.bysj.service.impl;


import com.cxxy.bysj.dao.ShopCartMapper;
import com.cxxy.bysj.entity.ShopCart;
import com.cxxy.bysj.entity.ShopCartExample;
import com.cxxy.bysj.entity.ShopCartKey;
import com.cxxy.bysj.service.ShopCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("addShopCart")
public class ShopCartServiceImpl implements ShopCartService {

    @Autowired(required = false)
    ShopCartMapper shopCartMapper;

    @Override
    public void addShopCart(ShopCart shopCart) {
        shopCartMapper.insertSelective(shopCart);
    }

    @Override
    public List<ShopCart> selectByExample(ShopCartExample shopCartExample) {
        return shopCartMapper.selectByExample(shopCartExample);
    }

    @Override
    public void deleteByKey(ShopCartKey shopCartKey) {
        shopCartMapper.deleteByPrimaryKey(shopCartKey);
    }

    @Override
    public void updateCartByKey(ShopCart shopCart) {
        shopCartMapper.updateByPrimaryKeySelective(shopCart);
    }

    @Override
    public ShopCart selectCartByKey(ShopCartKey shopCartKey) {
        return shopCartMapper.selectByPrimaryKey(shopCartKey);
    }
}
