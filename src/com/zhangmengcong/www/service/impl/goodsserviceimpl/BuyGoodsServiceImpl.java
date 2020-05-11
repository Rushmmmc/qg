package com.zhangmengcong.www.service.impl.goodsserviceimpl;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.goodsservice.BuyGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_SHOPPINGCAR;


/**
 * @author:zmc
 * @function: 买商品
 * @date: 2020/5/1 21:19
 */
public class BuyGoodsServiceImpl implements BuyGoodsService {

    @Override
    public String buyGoodsService(Indent indent, int ifShoppingCar,String username) {
        Factory factory = new Factory();
        //选择购物车功能
        if(ifShoppingCar == IF_SHOPPINGCAR){
            //只传入id 仅需检测id
            //该id其实是goods id
            boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(indent.getId()));
            if(ifIdFormatWrong){
                    return "订单id格式不正确┭┮﹏┭┮,仅支持整数";
                }
            Goods goods = factory.getGetPriceAndGoodsNameService().getPriceAndGoodsNameService(indent.getId());
            //检测该商品是否用户自己的商品 若是 拦截
            String sellerName = factory.getQueryDao().queryDao("seller","goods","goodsName",
                    "\""+goods.getGoodsName()+"\"");
            //必须都转化成小写或大写才能拦截全部情况
            if(sellerName.toLowerCase().equals(username.toLowerCase())){
                return "您不可以把自己的商品加入购物车┭┮﹏┭┮";
            }
            //检查是否已存在在购物车
            boolean ifGoodsInShoppingCar = factory.getCheckIfGoodsInShoppingCarDao().
                    checkIfGoodsInShoppingCar(username,goods.getGoodsName());
            if(ifGoodsInShoppingCar){
                return "该商品已在购物车,快去购物车下单吧";
            }
                indent.setGoodsName(goods.getGoodsName());
                indent.setSeller(goods.getSeller());
                indent.setPrice(goods.getPrice());
                indent.setGoodsType(goods.getType());
                indent.setAmount(1);
                indent.setActuallyPrice(goods.getPrice());
                indent.setTotalPrice(goods.getPrice());
                indent.setStatus("购物车");
                indent.setLastAmount(goods.getAmount());

                factory.getGenerateIndentDao().generateIndent(indent);

                return "商品已添加入购物车( •̀ ω •́ )y";
        }

        //普通购买功能
        //检验数据
        boolean ifAmountWrong = factory.getFormatService().formatService(String.valueOf(indent.getAmount()));
        boolean ifIntegralWrong = factory.getFormatService().formatService(String.valueOf(indent.getUseIntegral()));
        if(ifAmountWrong){
            return "数量格式不正确┭┮﹏┭┮,仅支持整数";
        }
        if(ifIntegralWrong){
            return "积分格式不正确┭┮﹏┭┮,仅支持整数";
        }


        //检测积分是否超过了总价
        if (indent.getUseIntegral() > indent.getPrice() * indent.getAmount()) {
            return "此商品不需要这么多积分┭┮﹏┭┮";
        }
        //检查积分是否足够
        int nowIntegral = Integer.parseInt(factory.getQueryDao().queryDao("integral","user"
                ,"username","\""+indent.getBuyer()+"\""));
        if(nowIntegral < indent.getUseIntegral()){
            return "您的积分不足";
        }
        int lastAmount = Integer.parseInt(factory.getQueryDao().queryDao("amount","goods"
                ,"goodsName","\""+indent.getGoodsName()+"\""));
        if(lastAmount < indent.getAmount()){
            return "商家存货不足,客官买少一点吧┭┮﹏┭┮";
        }

            //无误 插库
            indent.setTotalPrice(indent.getPrice()*indent.getAmount());
            indent.setActuallyPrice(indent.getTotalPrice()-indent.getUseIntegral());
            indent.setStatus("商家未接单");
            factory.getGenerateIndentDao().generateIndent(indent);
            //减少用户的积分
            factory.getUpdateDao().updateDao("user","integral","integral - "
                +indent.getUseIntegral(),"username","\""+indent.getBuyer()+"\"");

        //减少存货数
            factory.getUpdateDao().updateDao("goods","amount","amount - "
                +indent.getAmount(),"goodsName","\""+indent.getGoodsName()+"\"");
                return "商家正在火速处理您的订单( •̀ ω •́ )y";

    }
}
