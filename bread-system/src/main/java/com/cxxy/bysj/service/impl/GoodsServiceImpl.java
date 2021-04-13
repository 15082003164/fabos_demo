package com.cxxy.bysj.service.impl;


import com.cxxy.bysj.dao.GoodsMapper;
import com.cxxy.bysj.dao.ImagePathMapper;
import com.cxxy.bysj.entity.*;
import com.cxxy.bysj.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {

    @Autowired(required = false)
    GoodsMapper goodsMapper;

    @Autowired(required = false)
    ImagePathMapper imagePathMapper;



    @Override
    public Integer addGoods(Goods goods) {
        goodsMapper.insertSelective(goods);
        return goods.getGoodsid();
    }


    @Override
    public void addImagePath(ImagePath imagePath) {
        imagePathMapper.insertSelective(imagePath);
    }

    @Override
    public List<Goods> selectByExample(GoodsExample example) {
        return goodsMapper.selectByExampleWithBLOBs(example);
    }

    @Override
    public void deleteGoodsById(Integer goodsid) {

        goodsMapper.deleteByPrimaryKey(goodsid);
    }

    @Override
    public void updateGoodsById(Goods goods) {
        goodsMapper.updateByPrimaryKeySelective(goods);
    }

    @Override
    public List<ImagePath> findImagePath(Integer goodsid) {
        ImagePathExample imagePathExample = new ImagePathExample();
        imagePathExample.or().andGoodidEqualTo(goodsid);

        return imagePathMapper.selectByExample(imagePathExample);
    }

    @Override
    public Goods selectById(Integer goodsid) {
        return goodsMapper.selectByPrimaryKey(goodsid);
    }

    @Override
    public List<Goods> selectByExampleLimit(GoodsExample digGoodsExample) {
        return goodsMapper.selectByExampleWithBLOBsLimit(digGoodsExample);
    }

    @Override
    public int updateNumForOrderFinish(int goodsid,int num) {
        return goodsMapper.updateNumForOrderFinish(goodsid,num);
    }

    @Override
    public int selectNumByGoodsId(Integer goodsid) {
        return goodsMapper.selectNumByGoodsId(goodsid);
    }

}
