package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:51
 */
public interface BuyGoodsFromShoppingCarService {
    /** 在购物车列表中买商品
     *
     * @param indent 订单对象
     * @return 提示信息
     */
    String buyGoodsFromShoppingCar(Indent indent);
}
