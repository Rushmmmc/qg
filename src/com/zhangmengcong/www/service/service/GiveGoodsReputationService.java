package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:45
 */
public interface GiveGoodsReputationService {
    /**
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String giveGoodsReputation(int indentId);

    /**
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String giveBadReputation(int indentId);
}
