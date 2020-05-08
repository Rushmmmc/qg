package com.zhangmengcong.www.service.impl.goodsserviceimpl;

import com.zhangmengcong.www.service.service.goodsservice.GoodsRecommendService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/8 1:14
 */
public class GoodsRecommendServiceImpl implements GoodsRecommendService {
    @Override
    public String chooseGoodsRecommendService(String recommend, int indentId) {
        Factory factory = new Factory();
        boolean ifIndentIdFormatWrong = factory.getFormatService().formatService(String.valueOf(indentId));
        if(ifIndentIdFormatWrong){
            return "订单id仅支持整数┭┮﹏┭┮";
        }
        boolean ifRecommendFormatWrong = factory.getFormatService().ifIncludeSymbol(recommend);
        if(ifRecommendFormatWrong){
            return "推荐文字不可为空或包含特殊符号";
        }
        String goodsName = factory.getQueryDao().queryDao("goodsName",
                "indent","id",String.valueOf(indentId));
        factory.getUpdateDao().updateDao("goods","recommend"
                ,"\""+recommend+"\"","goodsName","\""+goodsName+"\"");
        return "推荐成功！";
    }
}
