package com.zhangmengcong.www.service.impl.goodsservice;

import com.zhangmengcong.www.service.service.goodsservice.SellGoodsToBuyerService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function: 发货、把订单状态更新为在路上
 * @date: 2020/5/1 20:53
 */
public class SellGoodsToBuyerServiceImpl implements SellGoodsToBuyerService {

    @Override
    public void sellGoodsToBuyerImpl(int id) {
        Factory factory = new Factory();
        factory.getGoodsDao().sellGoodsToBuyer(id);
    }
}
