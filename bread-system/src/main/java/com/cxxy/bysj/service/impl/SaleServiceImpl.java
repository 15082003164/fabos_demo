package com.cxxy.bysj.service.impl;

import com.cxxy.bysj.dao.SaleMapper;
import com.cxxy.bysj.entity.Sale;
import com.cxxy.bysj.service.SaleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("SaleService")
public class SaleServiceImpl implements SaleService {

    @Resource
    private SaleMapper saleMapper;

    @Override
    public List<Sale> selectAll() {
        return saleMapper.selectAll();
    }

    @Override
    public int insertByGoodsId(Sale sale) {
        return saleMapper.insertByGoodsId(sale);
    }

    @Override
    public int deleteByGoodsId(Integer goods_id) {
        return saleMapper.deleteByGoodsId(goods_id);
    }
}
