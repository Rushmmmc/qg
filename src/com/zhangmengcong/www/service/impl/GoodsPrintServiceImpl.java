package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.service.service.GoodsPrintService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 15:36
 */
public class GoodsPrintServiceImpl implements GoodsPrintService {
    Factory factory = new Factory();
    @Override
    public List<Goods> goodsPrintService(){
        return factory.getGoodsPrintDao().selectAllGoods(false,null);
    }
    @Override
    public List<Goods> sellerPersonalGoodsPrintService(String sellerName){
        return factory.getGoodsPrintDao().selectAllGoods(true,sellerName);
    }
}
