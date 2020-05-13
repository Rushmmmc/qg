package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function: 检测用户是不是买自己的商品(刷单?)
 * @date: 2020/5/11 10:09
 */
public interface CheckUserIfTheGoodsSellerService {
    /**
     *
     * @param username 用户名
     * @param goodsId 商品id
     * @return 提示信息
     */
   String checkUserIfTheGoodsSellerService(String username,int goodsId);
}
