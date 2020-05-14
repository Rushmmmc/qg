package com.zhangmengcong.www.service.service;

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
    void deleteGoodsService(int id);

    /** 管理员审核商品
     *
     * @param id 商品id
     */
    void passGoodsService(int id);

    /**
     * 选择调用哪个方法
     * @param username 用于检测用户权限是否足够
     * @param id 商品id
     * @param ifDelete 是否选择删除 否则商品审核通过
     * @return 提示信息
     */
    String deleteOrPassGoodsService(int id,int ifDelete,String username);


    /** 商家删除商品
     *
     * @param goodsId 商品id
     * @param sellerName 卖家名
     * @return 返回提示信息
     */
    String sellerDeleteGoods(int goodsId,String sellerName);
}
