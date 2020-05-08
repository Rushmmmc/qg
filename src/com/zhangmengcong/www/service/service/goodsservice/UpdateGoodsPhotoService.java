package com.zhangmengcong.www.service.service.goodsservice;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/8 11:33
 */
public interface UpdateGoodsPhotoService {
    /** 用于给商品上传图片
     *
     * @param photoName 图片名
     * @param goodsName 商品名
     * @return 提示信息
     */
    String updateGoodsPhotoService(String photoName,String goodsName);
}
