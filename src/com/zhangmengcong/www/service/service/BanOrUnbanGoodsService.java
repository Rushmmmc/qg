package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function: 解封/封禁商品
 * @date: 2020/5/13 19:14
 */
public interface BanOrUnbanGoodsService {
    /**
     *
     * @param ifBan 是否封禁 否则为解封
     * @param goodsId 商品id
     * @return 提示信息
     */
    String banOrUnbanGoodsService(int ifBan,int goodsId);
}
