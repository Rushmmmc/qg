package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.service.service.AddGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.ADD_GOODS_FAIL;
import static com.zhangmengcong.www.constant.GoodsConstant.ADD_GOODS_SUCCESS;

/**
 * @author:zmc
 * @function: 用户添加商品的实现类
 * @date: 2020/4/30 10:24
 */
public class AddGoodsServiceImpl implements AddGoodsService {
    @Override
    public String addGoodsService(Goods goods) {
        Factory factory = new Factory();
        boolean ifNameFormatError = factory.getFormatService().ifIncludeSymbol(goods.getGoodsName());
        if(ifNameFormatError){
            return "商品名不可为空或包含特殊符号";
        }

        boolean ifTypeFormatError = factory.getFormatService().ifIncludeSymbol(goods.getType());
        if(ifTypeFormatError){
            return "商品类型不可为空或包含特殊符号";
        }

        boolean ifPriceFormatError = factory.getFormatService().ifRadixPointLessTwo(String.valueOf(goods.getPrice()));
        if(ifPriceFormatError){
            return "商品价格仅支持数字";
        }


        boolean ifAmountFormatError = factory.getFormatService().formatService(String.valueOf(goods.getAmount()));
        if(ifAmountFormatError){
            return "商品数量仅支持整数且不能超过10亿┭┮﹏┭┮";
        }

        boolean ifMessageFormatError = factory.getFormatService().ifIncludeSymbol(goods.getImformation());
        if(ifMessageFormatError){
            return "商品信息不可包含特殊符号";
        }
        String checkGoodsNameIfExist = factory.getQueryDao().queryDao("goodsName","goods",
                "goodsName","\""+goods.getGoodsName()+"\"");
        if(!"".equals(checkGoodsNameIfExist)){
            return "该商品名已存在！,请更换商品名";
        }

        //检验完毕 插库
        //获取用户信誉分
        int sellerReputation = factory.getSelectPersonalMessageService().selectPersonalMessage(goods.getSeller()).get(0).getReputationPoint();
        goods.setSellerReputation(sellerReputation);
        if (factory.getAddGoodsDao().addGoods(goods)) {
                return ADD_GOODS_SUCCESS;
            } else {
                return ADD_GOODS_FAIL;
            }
    }
}
