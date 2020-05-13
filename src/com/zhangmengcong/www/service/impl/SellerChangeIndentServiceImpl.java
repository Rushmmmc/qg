package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.SellerChangeIndentService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:23
 */
public class SellerChangeIndentServiceImpl implements SellerChangeIndentService {
    @Override
    public String sellerChangeIndent(Indent indent) {
        Factory factory = new Factory();
        if (factory.getFormatService().ifIdFormatWrong(String.valueOf(indent.getId()))) {
            return "订单id格式错误";
        }
        boolean ifNameFormatError = factory.getFormatService().ifIncludeSymbol(indent.getGoodsName());
        if (ifNameFormatError) {
            return "商品名不可包含特殊符号┭┮﹏┭┮";
        }
        boolean ifPriceFormatError = factory.getFormatService().ifRadixPointLessTwo
                (String.valueOf(indent.getPrice()));
        if (ifPriceFormatError) {
            return "价格仅支持数字";
        }
        boolean ifAmountFormatError = factory.getFormatService().formatService
                (String.valueOf(indent.getAmount()));
        if (ifAmountFormatError) {
            return "商品仅支持整数";
        }
        //查询原订单卖出商品数
        int nowAmount = Integer.parseInt(factory.getQueryDao().queryDao("amount","indent","id",
                String.valueOf(indent.getId())));
        //现要修改的订单卖出商品数量
        int tempAmount = indent.getAmount();
        //先查询是否有足够的存货
        int totalAmount = Integer.parseInt(factory.getQueryDao().queryDao("amount","goods",
                "goodsName","\""+indent.getGoodsName()+"\""));
        if(tempAmount > totalAmount){
            return "存货不足啦，现存货数为"+totalAmount;
        }
        //若存货足够 修改存货数量
        factory.getUpdateDao().updateDao("goods","amount",
                "amount-"+ (tempAmount - nowAmount),"goodsName",
                "\""+indent.getGoodsName()+"\"");
        factory.getDeleteOrChangeDao().deleteOrChange(null, 0, 0,
                null, null, true, indent);
        return "修改成功，现存货数为"+(totalAmount-tempAmount+nowAmount);
    }
}
