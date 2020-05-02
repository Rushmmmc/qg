package com.zhangmengcong.www.service.service.goodsservice;

/**
 * @author:zmc
 * @function: 发货、把订单状态更新为在路上
 * @date: 2020/5/1 20:54
 */
public interface SellGoodsToBuyerService {
    /** 发货、把订单状态更新为在路上
     *
     * @param id
     */
    void sellGoodsToBuyerImpl(int id);
}
