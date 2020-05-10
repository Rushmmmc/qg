package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.service.service.indentservice.GiveGoodsReputationService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:45
 */
public class GiveGoodsReputationServiceImpl implements GiveGoodsReputationService {
    Factory factory = new Factory();
    @Override
    public String giveGoodsReputation(int indentId){
        if (factory.getFormatService().ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        String sellerName = factory.getQueryDao().queryDao
                ("seller","indent","id",String.valueOf(indentId));
        factory.getUpdateDao().updateDao("user","reputationPoint", "reputationPoint+1"
                ,null,null,"username","\""+sellerName+"\"");
        factory.getDeleteOrChangeDao().deleteOrChange("indent",0,indentId,"好评"
                ,"reputation",false,null);
        return "感谢您的好评( •̀ ω •́ )y";
    }
    @Override
    public String giveBadReputation(int indentId){
        if (factory.getFormatService().ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        String sellerName = factory.getQueryDao().queryDao("seller","indent"
                ,"id",String.valueOf(indentId));
        factory.getUpdateDao().updateDao("user","reputationPoint", "reputationPoint-1"
                ,null,null,"username","\""+sellerName+"\"");
        factory.getDeleteOrChangeDao().deleteOrChange("indent",0,indentId,"差评",
                "reputation",false,null);
        return "感谢您的差评┭┮﹏┭┮";
    }
}
