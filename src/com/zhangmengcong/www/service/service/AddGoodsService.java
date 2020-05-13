package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.po.Goods;

/**
 * @author:zmc
 * @function:
 * @date: 2020/4/30 10:24
 */
public interface AddGoodsService {
    /** 用户添加二手商品待管理员审核
     *
     * @param goods 包含商品各种信息的对象
     * @return 返回最终alert 的文字信息
     */
    String addGoodsService(Goods goods);
}
