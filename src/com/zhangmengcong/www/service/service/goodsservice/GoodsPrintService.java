package com.zhangmengcong.www.service.service.goodsservice;

import com.zhangmengcong.www.po.Goods;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印全部商品信息
 * @date: 2020/5/1 15:36
 */
public interface GoodsPrintService {
    /** 打印全部商品信息
     *
     * @return goods对象
     */
    List<Goods> goodsPrintService();
}
