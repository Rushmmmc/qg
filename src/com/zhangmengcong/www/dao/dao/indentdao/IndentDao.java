package com.zhangmengcong.www.dao.dao.indentdao;

import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function: 订单的数据处理
 * @date: 2020/5/2 10:48
 */
public interface IndentDao {
    /**用户买商品 生成订单
     *
     * @param indent 封装好的订单对象
     */
    void buyGoods(Indent indent);

    /** 检测商品是否存在购物车订单
     *
     * @param username 用户名
     * @param goodsName 商品名
     * @return 是否存在
     */
    boolean checkIfGoodsInShoppingCar(String username,String goodsName);


}
