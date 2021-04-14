package com.cxxy.bysj.service;

import com.cxxy.bysj.entity.Sale;

import java.util.List;

public interface SaleService {

    public List<Sale> selectAll();


    public int insertByGoodsId(Sale sale);


    public int deleteByGoodsId(Integer goods_id);
}
