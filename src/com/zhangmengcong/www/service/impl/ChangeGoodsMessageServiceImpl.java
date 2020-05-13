package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.service.service.ChangeGoodsMessageService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/11 15:59
 */
public class ChangeGoodsMessageServiceImpl implements ChangeGoodsMessageService {
    @Override
    public String changeGoodsMessageService(Goods goods) {
        Factory factory = new Factory();
        //检验数据格式
        boolean ifGoodsIdFormatWrong = factory.getFormatService().formatService(String.valueOf(goods.getId()));
        if(ifGoodsIdFormatWrong){
            return "商品id格式有误！";
        }
        boolean ifGoodsNameFormatWrong = factory.getFormatService().ifIncludeSymbol(goods.getGoodsName());
        if(ifGoodsNameFormatWrong){
            return "商品名格式有误，请不要包含空格或符号";
        }
        boolean ifGoodsPriceFormatWrong = factory.getFormatService().ifRadixPointLessTwo(String.valueOf(goods.getId()));
        if(ifGoodsPriceFormatWrong){
            return "商品单价格式有误";
        }
        boolean ifGoodsAmountFormatWrong = factory.getFormatService().formatService(String.valueOf(goods.getAmount()));
        if(ifGoodsAmountFormatWrong){
            return "商品存货数量格式有误";
        }

        //检测该商品名是否存在
        //获取商品的现商品名
        String nowGoodsName = factory.getQueryDao().queryDao("goodsName","goods","id",
                String.valueOf(goods.getId()));
        //如果修改了商品名
        if(!nowGoodsName.equals(goods.getGoodsName())){
            //检测该商品名是否已经存在
            String tempGoodsName = factory.getQueryDao().queryDao("goodsName","goods","goodsName",
                    "\""+goods.getGoodsName()+"\"");
            //如果名字不为空 即已存在
            if(!"".equals(tempGoodsName)){
                return "该商品名已被占用！换一个吧┭┮﹏┭┮";
            }
            //检测是否还有订单未完成
            String tempGoodsName2 = factory.getQueryDao().queryDao2("goodsName","indent","status",
                    "'商品在路上'","goodsName","\""+nowGoodsName+"\"");
            String tempGoodsName3 = factory.getQueryDao().queryDao2("goodsName","indent","status",
                    "'商家未结单'","goodsName","\""+nowGoodsName+"\"");
            String tempGoodsName4 = factory.getQueryDao().queryDao2("goodsName","indent","status",
                    "'用户申请退货'","goodsName","\""+nowGoodsName+"\"");
            if(!"".equals(tempGoodsName2) || !"".equals(tempGoodsName3) || !"".equals(tempGoodsName4) ){
                return "请先完成未接单、未收货、未处理的退货订单再修改商品信息！";
            }
        }
        //检验完毕 可以改
        factory.getUpdateDao().updateDao("goods","goodsName","\""+goods.getGoodsName()+"\"","id",
                String.valueOf(goods.getId()));
        factory.getUpdateDao().updateDao("goods","price","\""+goods.getPrice()+"\"","id",
                String.valueOf(goods.getId()));
        factory.getUpdateDao().updateDao("goods","amount","\""+goods.getAmount()+"\"","id",
                String.valueOf(goods.getId()));
        return "修改成功！";
    }
}
