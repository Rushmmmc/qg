package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:37
 */
public interface SellerSendGoodsService {
    /** 商家发货
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String sellerSendGoods(int indentId);
}
