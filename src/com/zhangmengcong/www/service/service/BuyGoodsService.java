package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function: 用户购买商品 生成订单
 * @date: 2020/5/1 21:19
 */
public interface BuyGoodsService {
    /** 用户购买商品 生成订单
     * @param indent 封装好的订单对象
     * @param ifShoppingCar  是否使用添加购物车功能
     * @param username 买家名
     * @return 提示信息
     */
    String buyGoodsService(Indent indent, int ifShoppingCar,String username);
}
