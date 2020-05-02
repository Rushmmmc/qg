package com.zhangmengcong.www.service.impl.goodsservice;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.goodsservice.BuyGoodsService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 21:19
 */
public class BuyGoodsServiceImpl implements BuyGoodsService {

    @Override
    public void sellGoodsService(Indent indent) {
        Factory factory = new Factory();
        indent.setTotalPrice(indent.getPrice()*indent.getAmount());
        factory.getGoodsDao().buyGoods(indent);
    }
}
