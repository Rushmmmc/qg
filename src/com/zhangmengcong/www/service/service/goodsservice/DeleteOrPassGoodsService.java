package com.zhangmengcong.www.service.service.goodsservice;

/**
 * @author:zmc
 * @function: 删除或通过商品
 * @date: 2020/5/1 16:39
 */
public interface DeleteOrPassGoodsService {
    /** 删除或通过商品
     *
     * @param id 商品id
     * @param ifDelete 是否删除 否 则选择通过功能
     */
    void deleteOrPassGoodsService(int id,int ifDelete);
}
