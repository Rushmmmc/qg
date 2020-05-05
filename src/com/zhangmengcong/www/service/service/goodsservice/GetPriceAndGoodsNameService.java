package com.zhangmengcong.www.service.service.goodsservice;

import com.zhangmengcong.www.po.Goods;

/**
 * @author:zmc
 * @function: 利用商品id 获得单价和商品名 用于生成订单
 * @date: 2020/5/4 15:46
 */
public interface GetPriceAndGoodsNameService {
    /**
     *
     * @param id 商品id
     * @return 存储单价和商品名的goods对象
     */
    Goods getPriceAndGoodsNameService(int id);
}
