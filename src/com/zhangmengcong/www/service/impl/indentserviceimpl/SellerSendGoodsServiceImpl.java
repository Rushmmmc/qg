package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.service.service.indentservice.SellerSendGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.INDENT_HAVE_SEND;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:37
 */
public class SellerSendGoodsServiceImpl implements SellerSendGoodsService {
    @Override
    public String sellerSendGoods(int indentId) {
        Factory factory = new Factory();
        if (factory.getFormatService().ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        //商家发货
        String stauts = factory.getQueryDao().queryDao("status",
                "indent", "id", String.valueOf(indentId));
        //已发货
        if (stauts.contains(INDENT_HAVE_SEND)) {
            return "商品已在路上,请不要重复发货";
        }
        factory.getDeleteOrChangeDao().deleteOrChange("indent", 0, indentId,
                "商品在路上", "status", false, null);
        return "发货成功";
    }
}
