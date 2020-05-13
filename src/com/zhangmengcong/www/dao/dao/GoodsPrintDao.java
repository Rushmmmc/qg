package com.zhangmengcong.www.dao.dao;

import com.zhangmengcong.www.po.Goods;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/4/30 18:06
 */
public interface GoodsPrintDao {
    /**给管理员打印全部商品信息 便于删除 审核商品 或用于商家个人商品管理
     * @param ifPersonalGoods 是否选择打印个人商家商品功能
     * @return 商品List
     */
    List<Goods> selectAllGoods(boolean ifPersonalGoods,String seller);
}
