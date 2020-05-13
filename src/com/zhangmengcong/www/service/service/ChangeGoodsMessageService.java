package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.po.Goods;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/11 15:59
 */
public interface ChangeGoodsMessageService {
    /**
     *
     * @param goods 商品对象
     * @return 提示信息
     */
    public String changeGoodsMessageService(Goods goods);
}
