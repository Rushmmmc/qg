package com.zhangmengcong.www.dao.dao.indentdao;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 15:18
 */
public interface CheckIfGoodsInShoppingCarDao {
    /** 检测商品是否存在购物车订单
     *
     * @param username 用户名
     * @param goodsName 商品名
     * @return 是否存在
     */
    boolean checkIfGoodsInShoppingCar(String username,String goodsName);
}
