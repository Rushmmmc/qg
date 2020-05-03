package com.zhangmengcong.www.service.service.goodsservice;

/**
 * @author:zmc
 * @function: 删除或通过商品
 * @date: 2020/5/1 16:39
 */
public interface DeleteOrPassGoodsService {

    /** 删除商品
     *
     * @param id 商品id
     */
    void deletGoodsService(int id);

    /** 管理员审核商品
     *
     * @param id 商品id
     */
    void passGoodsService(int id);

    /**
     * 选择调用哪个方法
     * @param id 商品id
     * @param ifDelete 是否选择删除 否则商品审核通过
     */
    void deleteOrPassGoodsService(int id,int ifDelete);
}
