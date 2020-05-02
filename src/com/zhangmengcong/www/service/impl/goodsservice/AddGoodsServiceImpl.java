package com.zhangmengcong.www.service.impl.goodsservice;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.service.service.goodsservice.AddGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.ADD_GOODS_FAIL;
import static com.zhangmengcong.www.constant.GoodsConstant.ADD_GOODS_SUCCESS;

/**
 * @author:zmc
 * @function: 用户添加商品的实现类
 * @date: 2020/4/30 10:24
 */
public class AddGoodsServiceImpl implements AddGoodsService {
    @Override
    public String addGoodsService(Goods goods) {
        Factory factory = new Factory();
        if (factory.getGoodsDao().addGoods(goods)) {
            return ADD_GOODS_SUCCESS;
        } else {
            return ADD_GOODS_FAIL;
        }
    }
}
