package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.indentservice.IndentService;
import com.zhangmengcong.www.util.Factory;




import static com.zhangmengcong.www.constant.GoodsConstant.*;
import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;


/**
 * @author:zmc
 * @function: 分配方法 处理订单
 * @date: 2020/5/2 12:21
 */
public class IndentServiceImpl implements IndentService {
    Factory factory = new Factory();

    @Override
    public boolean ifIndentIdFormatWrong(int indentId) {
        return factory.getFormatService().formatService(String.valueOf(indentId));
    }

    @Override
    public String deleteIndent(int ifSeller, int indentId){
        if(ifSeller ==IF_SELLER){
            return sellerDeleteIndent(indentId);
        }else {
            return buyerDeleteIndent(indentId);
        }
    }
    @Override
    public String buyerDeleteIndent(int indentId) {
        if (ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        //用户删除订单
        factory.getUpdateDao().updateDao("indent", "ifBuyerDelete",
                "1", "id", "\"" + indentId + "\"");
        return "订单已删除！";
    }

    @Override
    public String sellerDeleteIndent(int indentId) {
        if (ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        //商家删除订单
        factory.getUpdateDao().updateDao("indent", "ifSellerDelete",
                "1", "id", "\"" + indentId + "\"");
        return "订单已删除！";
    }

    @Override
    public String sellerChangeIndent(Indent indent) {
        if (ifIndentIdFormatWrong(indent.getId())) {
            return "订单id格式错误";
        }
        boolean ifNameFormatError = factory.getFormatService().ifIncludeSymbol(indent.getGoodsName());
        if (ifNameFormatError) {
            return "商品名不可包含特殊符号┭┮﹏┭┮";
        }
        boolean ifPriceFormatError = factory.getFormatService().ifRadixPointLessTwo
                (String.valueOf(indent.getPrice()));
        if (ifPriceFormatError) {
            return "价格仅支持数字";
        }
        boolean ifAmountFormatError = factory.getFormatService().formatService
                (String.valueOf(indent.getAmount()));
        if (ifAmountFormatError) {
            return "商品仅支持整数";
        }

        factory.getDeleteOrChangeDao().deleteOrChange(null, 0, 0,
                null, null, true, indent);
        return "修改完成！";
    }

    @Override
    public String finishIndent(int indentId, String username) {
        if (ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        factory.getDeleteOrChangeDao().deleteOrChange("indent", 0,
                indentId, "订单已完成", "status"
                , false, null);
        //完成订单 经验值和积分加一
        factory.getUpdateDao().updateDao("user", "exp", "exp+1",
                "integral", "integral+1", "username",
                "\"" + username + "\"");
        return "订单已完成";
    }

    @Override
    public String sellerSendGoods(int indentId) {
        if (ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        //商家发货
        String stauts = factory.getQueryDao().queryDao("status",
                "indent", "id", String.valueOf(indentId));
        //已发货
        if (stauts.contains(INDENT_HAVE_SEND)) {
            return "商品已在路上,请不要重复发货";
        }
        factory.getDeleteOrChangeDao().deleteOrChange("indent", 0, indentId,
                "商品在路上", "status", false, null);
        return "发货成功";
    }




    @Override
    public String deleteShoppingCarIndent(int indentId) {
        if (ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        factory.getDeleteOrChangeDao().deleteOrChange("indent", IF_DELETE,
                indentId, null, null, false, null);
        //用户删除购物车信息
        return "删除成功！";
    }

    @Override
    public String buyGoodsFromShoppingCar(Indent indent){
                int indentId = indent.getId();
                if (ifIndentIdFormatWrong(indentId)) {
                    return "订单id格式错误";
                }
                boolean ifAmountFormatWrong = factory.getFormatService().formatService(
                        String.valueOf(indent.getAmount()));
                if(ifAmountFormatWrong) {
                    return "商品数量格式错误";
                }
                boolean ifIntegralFormatWrong = factory.getFormatService().formatService(
                        String.valueOf(indent.getUseIntegral()));
                if(ifIntegralFormatWrong) {
                    return "积分格式有误";
                }
                int nowIntegral = Integer.parseInt(factory.getQueryDao().queryDao("integral","user"
                        ,"username","\""+indent.getBuyer()+"\""));
                if(nowIntegral < indent.getUseIntegral()){
                    return "您的积分不足";
                }
                //检查用户输入数量是否超过存货
                int lastAmount = Integer.parseInt(factory.getQueryDao().queryDao("lastAmount","indent","id",
                        String.valueOf(indentId)));
                if(lastAmount < indent.getAmount()){
                    return "商家存货不足啦┭┮﹏┭┮,请买少一点吧";
                }
                float totalPrice = indent.getAmount() * indent.getPrice();
                factory.getUpdateDao().updateDao("indent","status",
                        "'商家未接单'","id",String.valueOf(indentId));
                factory.getUpdateDao().updateDao("indent","amount",
                        String.valueOf(indent.getAmount()),"id",String.valueOf(indentId));
                factory.getUpdateDao().updateDao("indent","totalPrice",String.valueOf(totalPrice),
                        "id",String.valueOf(indentId));
                factory.getUpdateDao().updateDao("indent","userIntegral",
                        String.valueOf(indent.getUseIntegral()),"id",String.valueOf(indentId));
                factory.getUpdateDao().updateDao("indent","actuallyPrice",
                        String.valueOf(totalPrice - indent.getUseIntegral()),"id",
                        String.valueOf(indentId));
                //商品存量减少
                factory.getUpdateDao().updateDao("goods", "amount", "amount - "
                        + indent.getAmount(), "goodsName",
                        "\"" + indent.getGoodsName() + "\"");
        System.out.println(indent);
                return "购买成功！";

}
            @Override
            public String giveGoodsReputation(int indentId){
                if (ifIndentIdFormatWrong(indentId)) {
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
                if (ifIndentIdFormatWrong(indentId)) {
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

    @Override
    public String userEvaluateIndent(String evaluate, int indentId) {
        Factory factory = new Factory();

        boolean ifEvaluateFormatWrong = factory.getFormatService().ifIncludeSymbol(evaluate);
        if(ifEvaluateFormatWrong){
            return "评价文字不可包含特殊符号┭┮﹏┭┮";
        }
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(indentId));
        if(ifIdFormatWrong){
            return "id仅支持整数┭┮﹏┭┮";
        }
        factory.getUpdateDao().updateDao("indent","evaluate","\""+evaluate+"\"","id",String.valueOf(indentId));
        return "评价成功( •̀ ω •́ )y";
    }
}
