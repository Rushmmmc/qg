package com.zhangmengcong.www.service.impl.goodsserviceimpl;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.goodsservice.BuyGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_SHOPPINGCAR;
import static com.zhangmengcong.www.constant.IndentConstant.SHOPPING_CAR_FUNCTION;


/**
 * @author:zmc
 * @function: 卖商品
 * @date: 2020/5/1 21:19
 */
public class BuyGoodsServiceImpl implements BuyGoodsService {

    @Override
    public String buyGoodsService(Indent indent, int ifShoppingCar) {
        Factory factory = new Factory();
        //选择购物车功能
        if(ifShoppingCar == IF_SHOPPINGCAR){
            //只传入id 仅需检测id
            boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(indent.getId()));
            if(!ifIdFormatWrong){
                Goods goods = factory.getGetPriceAndGoodsNameService().getPriceAndGoodsNameService(indent.getId());
                indent.setGoodsName(goods.getGoodsName());
                indent.setSeller(goods.getSeller());
                indent.setPrice(goods.getPrice());
                indent.setGoodsType(goods.getType());
                indent.setAmount(1);
                indent.setActuallyPrice(goods.getPrice());
                indent.setTotalPrice(goods.getPrice());
                indent.setStatus("购物车");
                factory.getIndentDao().buyGoods(indent);
                return "商品已添加入购物车( •̀ ω •́ )y";
            }else {
                return "数据格式不正确┭┮﹏┭┮";
            }
        }
        //用户仅输入这两个 所以仅检验这两个
        boolean ifAmountWrong = factory.getFormatService().formatService(String.valueOf(indent.getAmount()));
        boolean ifIntegralWrong = factory.getFormatService().formatService(String.valueOf(indent.getUseIntegral()));

        if(!ifAmountWrong && !ifIntegralWrong){
            indent.setTotalPrice(indent.getPrice()*indent.getAmount());
            indent.setActuallyPrice(indent.getTotalPrice()-indent.getUseIntegral());
            factory.getIndentDao().buyGoods(indent);
                indent.setStatus("商家未接单");
                return "商家正在火速处理您的订单( •̀ ω •́ )y";

        }
        return "数据格式不正确┭┮﹏┭┮";
    }
}
