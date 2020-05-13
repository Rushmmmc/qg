package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.service.service.GetPriceAndGoodsNameService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function: 利用商品id 获得单价和商品名 用于生成订单
 * @date: 2020/5/4 15:46
 */
public class GetPriceAndGoodsNameServiceImpl implements GetPriceAndGoodsNameService {
    @Override
    public Goods getPriceAndGoodsNameService(int id) {
        Factory factory = new Factory();
        float price = Float.parseFloat((factory.getQueryDao().queryDao("price","goods","id",String.valueOf(id))));
        String goodsName = factory.getQueryDao().queryDao("goodsName","goods","id",String.valueOf(id));
        String seller = factory.getQueryDao().queryDao("seller","goods","id",String.valueOf(id));
        String type = factory.getQueryDao().queryDao("type","goods","id",String.valueOf(id));
        int amout = Integer.parseInt(factory.getQueryDao().queryDao("amount","goods"
                ,"id",String.valueOf(id)));
        Goods goods = new Goods();
        goods.setPrice(price);
        goods.setGoodsName(goodsName);
        goods.setSeller(seller);
        goods.setType(type);
        goods.setAmount(amout);
        return goods;
    }
}
