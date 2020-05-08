package com.zhangmengcong.www.service.service.goodsservice;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/8 1:14
 */
public interface GoodsRecommendService {
    /**
     *
     * @param recommend 用于推荐的文字
     * @param indentId 订单id 用于修改商品推荐文字
     * @return 提示信息
     */
    String chooseGoodsRecommendService(String recommend,int indentId);
}
