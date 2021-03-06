package com.zhangmengcong.www.dao.dao;

import com.zhangmengcong.www.po.Indent;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印个人订单
 * @date: 2020/5/1 20:12
 */
public interface IndentPrintDao {
    /**打印个人订单
     * @param id 不为null则根据订单id查询单条信息
     * @param ifShoppingCar 是否使用打印购物车信息功能
     * @param ifSeller 是否筛选出售者的订单 否则筛选购买者自己的
     * @param username 用户名
     * @return 个人订单
     */
    List<Indent> selectPersonalIndent(int id,String username,int ifSeller,boolean ifShoppingCar);
}
