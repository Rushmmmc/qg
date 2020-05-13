package com.zhangmengcong.www.dao.dao;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 15:11
 */
public interface ChangeSellerAllGoodsReputationDao {
    /** 修改商家所有商品的信誉分
     *
     * @param sellerName 商户名
     * @param ifAdd 是否增加信誉分 否则减少
     * @return  是否成功
     */
    boolean changeSellerAllGoodsReputation(String sellerName,boolean ifAdd);
}
