package com.zhangmengcong.www.dao.dao.goodsdao;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 15:11
 */
public interface ChangeSellerAllGoodsReputationDao {
    /** 修改商家所有商品的信誉分
     *
     * @param sellerName 卖家名
     * @return  是否成功
     */
    boolean changeSellerAllGoodsReputation(String sellerName);
}
