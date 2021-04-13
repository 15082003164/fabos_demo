package com.cxxy.bysj.service;


import com.cxxy.bysj.entity.*;

import java.util.List;

public interface GoodsService {
    public Integer addGoods(Goods goods);

    public void addImagePath(ImagePath imagePath);

    public List<Goods> selectByExample(GoodsExample example);

    public void deleteGoodsById(Integer goodsid);

    public void updateGoodsById(Goods goods);

    public List<ImagePath> findImagePath(Integer goodsid);

    public Goods selectById(Integer goodsid);

    public List<Goods> selectByExampleLimit(GoodsExample digGoodsExample);

    public int updateNumForOrderFinish(int goodsid,int num);

    public int selectNumByGoodsId(Integer goodsid);

}
