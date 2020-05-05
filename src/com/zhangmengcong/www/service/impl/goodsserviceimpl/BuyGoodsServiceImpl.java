package com.zhangmengcong.www.service.impl.goodsserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.goodsservice.BuyGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SHOPPING_CAR;

/**
 * @author:zmc
 * @function: 卖商品
 * @date: 2020/5/1 21:19
 */
public class BuyGoodsServiceImpl implements BuyGoodsService {

    @Override
    public String buyGoodsService(Indent indent, int ifShoppingCar) {
        Factory factory = new Factory();
        //用户仅输入这两个 所以仅检验这两个
        boolean ifAmountWrong = factory.getFormatService().formatService(String.valueOf(indent.getAmount()));
        boolean ifIntegralWrong = factory.getFormatService().formatService(String.valueOf(indent.getUseIntegral()));

        if(!ifAmountWrong && !ifIntegralWrong){
            indent.setTotalPrice(indent.getPrice()*indent.getAmount());
            indent.setActuallyPrice(indent.getTotalPrice()-indent.getUseIntegral());
            factory.getIndentDao().buyGoods(indent);
            if(ifShoppingCar == IF_SHOPPING_CAR){
                indent.setStatus("购物车");
                return "添加购物车成功！( •̀ ω •́ )y";
            }else {
                indent.setStatus("商家未接单");
                return "商家正在火速处理您的订单( •̀ ω •́ )y";
            }
        }
        return "数据格式不正确┭┮﹏┭┮";
    }
}
