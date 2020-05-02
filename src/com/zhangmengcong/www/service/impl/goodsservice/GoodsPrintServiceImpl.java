package com.zhangmengcong.www.service.impl.goodsservice;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.service.service.goodsservice.GoodsPrintService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 15:36
 */
public class GoodsPrintServiceImpl implements GoodsPrintService {
    @Override
    public List<Goods> goodsPrintService(){
        Factory factory = new Factory();
        return factory.getGoodsPrintDao().selectAllGoods();
    }
}
