package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:48
 */
public interface RejectSellGoodsService {
    /**
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String rejectSellGoods(int indentId);
}
