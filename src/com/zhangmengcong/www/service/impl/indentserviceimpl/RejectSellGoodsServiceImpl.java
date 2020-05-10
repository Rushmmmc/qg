package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.service.service.indentservice.RejectSellGoodsService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:48
 */
public class RejectSellGoodsServiceImpl implements RejectSellGoodsService {
    @Override
    public String rejectSellGoods(int indentId) {
        Factory factory = new Factory();
        if (factory.getFormatService().ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        factory.getUpdateDao().updateDao("indent","status",
                "'商家拒绝售卖'","id",String.valueOf(indentId));
        return "已拒绝订单";
    }
}
