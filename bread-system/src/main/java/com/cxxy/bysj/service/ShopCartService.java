package com.cxxy.bysj.service;


import com.cxxy.bysj.entity.ShopCart;
import com.cxxy.bysj.entity.ShopCartExample;
import com.cxxy.bysj.entity.ShopCartKey;

import java.util.List;

public interface ShopCartService {
    public void addShopCart(ShopCart shopCart);

    public List<ShopCart> selectByExample(ShopCartExample shopCartExample);

    public void deleteByKey(ShopCartKey shopCartKey);

    public void updateCartByKey(ShopCart shopCart);

    public ShopCart selectCartByKey(ShopCartKey shopCartKey);
}
