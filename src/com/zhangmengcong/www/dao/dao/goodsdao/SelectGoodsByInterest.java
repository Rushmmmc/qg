package com.zhangmengcong.www.dao.dao.goodsdao;

import com.zhangmengcong.www.po.Goods;

import java.util.List;

/**
 * @author:zmc
 * @function: 实现简陋的智能推送功能
 * @date: 2020/5/3 16:27
 */
public interface SelectGoodsByInterest {
    /** 实现简陋的智能推送功能
     *
     * @param username 给该用户智能推送
     * @return 推送的商品
     */
    List<Goods> selectGoodsByInterest(String username);
}
