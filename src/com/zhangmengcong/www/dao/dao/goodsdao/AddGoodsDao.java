package com.zhangmengcong.www.dao.dao.goodsdao;

import com.zhangmengcong.www.po.Goods;

/**
 * @author:zmc
 * @function: 商品相关的数据处理
 * @date: 2020/4/30 10:11
 */
public interface AddGoodsDao {
    /** 用户提交商品给管理员审核
     *
     * @param goods 包含商品各种信息的对象
     * @return 是否成功
     */
    boolean addGoods(Goods goods);



}
