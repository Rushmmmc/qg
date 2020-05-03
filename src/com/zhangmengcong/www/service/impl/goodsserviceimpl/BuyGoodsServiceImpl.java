package com.zhangmengcong.www.service.impl.goodsserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.goodsservice.BuyGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SHOPPING_CAR;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 21:19
 */
public class BuyGoodsServiceImpl implements BuyGoodsService {

    @Override
    public void sellGoodsService(Indent indent,int ifShoppingCar) {
        Factory factory = new Factory();
        if(ifShoppingCar == IF_SHOPPING_CAR){
            indent.setStatus("购物车");
        }else {
            indent.setStatus("商家未接单");
        }
        indent.setTotalPrice(indent.getPrice()*indent.getAmount());
        indent.setActuallyPrice(indent.getTotalPrice()-indent.getUseIntegral());
        factory.getIndentDao().buyGoods(indent);
    }
}
