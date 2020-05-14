package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.ManageAppealService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.AdminConstant.*;
import static com.zhangmengcong.www.constant.UserConstant.ADMIN_LEVEL;

/**
 * @author:zmc
 * @function: 扣除被投诉商家的信誉分
 * @date: 2020/5/3 21:06
 */
public class ManageAppealServiceImpl implements ManageAppealService {
    @Override
    public void minusSellerReputationService(String username) {
        Factory factory = new Factory();
        factory.getUpdateDao().updateDao("user","reputationPoint","reputationPoint - 1",
                "username","\""+username+"\"");
    }

    @Override
    public String manageAppealService(String type,int id,int appealId,String username) {
        Factory factory = new Factory();
        //检测是否管理员
        int level = Integer.parseInt(factory.getQueryDao().queryDao("level","user","username",
                "\""+username+"\""));
        if(level != ADMIN_LEVEL){
            return "您的权限不足，只有管理员可以处理申诉";
        }
        //判空 判断格式
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(id));
        boolean ifTypeFormatWrong = factory.getFormatService().formatService(type);

        if(ifIdFormatWrong){
            return "订单id格式不正确!┭┮﹏┭┮";
        }
        if(ifTypeFormatWrong){
            return "处理类型格式不正确~~~~(>_<)~~~~";
        }

        String message = "信息格式不正确!┭┮﹏┭┮";
        String sellerName = factory.getQueryDao().queryDao("seller","indent","id",String.valueOf(id));
        String buyerName = factory.getQueryDao().queryDao("buyer","indent","id",String.valueOf(id));
        //处理投诉商家
        if(COMPLAINT_SELLER.equals(type)){
            //查询商品名
            //扣除商家所有商品中的信誉分
            boolean ifSuccess = factory.getChangeSellerAllGoodsReputationDao().changeSellerAllGoodsReputation(sellerName,false);
            if(ifSuccess) {
                minusSellerReputationService(sellerName);
                factory.getUpdateDao().updateDao("appeal","status","\"扣除商家信誉分\"","id","\""+appealId+"\"");
                message = "该商家的所有商品的信誉分已扣除";
            }else {
                return "修改失败,可能是订单已被删除";
            }
        }
        //处理交易维权
        if(DEFEND_LEGAL_RIGHT.equals(type)){
            float actuallyPrice = Float.parseFloat(factory.getQueryDao().queryDao("actuallyPrice","indent","id",String.valueOf(id)));
            int integral = 2 * Integer.parseInt(factory.getQueryDao().queryDao("useIntegral","indent","id",String.valueOf(id)));
            boolean ifSuccess = factory.getChangeSellerAllGoodsReputationDao().changeSellerAllGoodsReputation(sellerName,false);
            if(ifSuccess) {
                //扣除卖家信誉分
                minusSellerReputationService(sellerName);
                factory.getUpdateDao().updateDao("appeal","status",
                        "\"退款、返还双倍积分并且扣除商家信誉分\"","id","\""+appealId+"\"");
                factory.getUpdateDao().updateDao("user","integral","integral + "+ integral,
                        null,null,"username","\""+buyerName+"\"");
                message = "该商家的所有商品的信誉分已扣除、退款"+actuallyPrice+"元 返还使用的双倍积分共 "+integral +"分";
            }else {
                return "修改失败,可能是订单已被删除";
            }
        }
    return message;
    }
}
