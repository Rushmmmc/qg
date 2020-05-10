package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.indentservice.BuyGoodsFromShoppingCarService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:51
 */
public class BuyGoodsFromShoppingCarServiceImpl implements BuyGoodsFromShoppingCarService {
    @Override
    public String buyGoodsFromShoppingCar(Indent indent) {
        int indentId = indent.getId();
        Factory factory = new Factory();

        //检验数据
        if (factory.getFormatService().ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        boolean ifAmountFormatWrong = factory.getFormatService().formatService(
                String.valueOf(indent.getAmount()));
        if (ifAmountFormatWrong) {
            return "商品数量格式错误";
        }
        boolean ifIntegralFormatWrong = factory.getFormatService().formatService(
                String.valueOf(indent.getUseIntegral()));
        if (ifIntegralFormatWrong) {
            return "积分格式有误";
        }
        int nowIntegral = Integer.parseInt(factory.getQueryDao().queryDao("integral", "user"
                , "username", "\"" + indent.getBuyer() + "\""));
        if (nowIntegral < indent.getUseIntegral()) {
            return "您的积分不足";
        }
        //检查用户输入数量是否超过存货
        int lastAmount = Integer.parseInt(factory.getQueryDao().queryDao("lastAmount", "indent", "id",
                String.valueOf(indentId)));
        if (lastAmount < indent.getAmount()) {
            return "商家存货不足啦┭┮﹏┭┮,请买少一点吧";
        }
        //检测积分是否超过了总价
        float totalPrice = indent.getAmount() * indent.getPrice();
        if(indent.getUseIntegral() > indent.getTotalPrice()){
            return "该商品不需要这么多积分┭┮﹏┭┮";
        }
        //检验数据完毕

        //数据没问题 插库
        //更新订单状态
        factory.getUpdateDao().updateDao("indent", "status",
                "'商家未接单'", "id", String.valueOf(indentId));
        //更新订单数量
        factory.getUpdateDao().updateDao("indent", "amount",
                String.valueOf(indent.getAmount()), "id", String.valueOf(indentId));
        //更新订单总价
        factory.getUpdateDao().updateDao("indent", "totalPrice", String.valueOf(totalPrice),
                "id", String.valueOf(indentId));
        //更新订单使用积分
        factory.getUpdateDao().updateDao("indent", "useIntegral",
                String.valueOf(indent.getUseIntegral()), "id", String.valueOf(indentId));
        //更新实际付款
        factory.getUpdateDao().updateDao("indent", "actuallyPrice",
                String.valueOf(totalPrice - indent.getUseIntegral()), "id",
                String.valueOf(indentId));
        //减少用户的积分
        factory.getUpdateDao().updateDao("user","integral","integral - "+indent.getUseIntegral(),
                "username","\""+indent.getBuyer()+"\"");
        //商品存量减少
        factory.getUpdateDao().updateDao("goods", "amount", "amount - "
                        + indent.getAmount(), "goodsName",
                "\"" + indent.getGoodsName() + "\"");
        return "购买成功！";
    }
}