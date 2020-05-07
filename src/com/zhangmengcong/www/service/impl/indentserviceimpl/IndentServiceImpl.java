package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.indentservice.IndentService;
import com.zhangmengcong.www.util.Factory;




import static com.zhangmengcong.www.constant.GoodsConstant.*;
import static com.zhangmengcong.www.constant.IndentConstant.*;
import static com.zhangmengcong.www.constant.PageConstant.SELL;

/**
 * @author:zmc
 * @function: 分配方法 处理订单
 * @date: 2020/5/2 12:21
 */
public class IndentServiceImpl implements IndentService {

    @Override
    public String indentSelectMethod(String method,int id,Indent indent,String message,String username) {
        Factory factory = new Factory();
        //删除订单
        if(DELETE_INDENT.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent", IF_DELETE, id, null, null, false, null);
        }
        //改变订单商品个数 金额等信息
        if(CHANGE_INDENT.equals(method)){
            boolean ifIdFormatError = factory.getFormatService().formatService(String.valueOf(indent.getId()));
            if(ifIdFormatError){
                return "id格式错误┭┮﹏┭┮";
            }
            boolean ifNameFormatError = factory.getFormatService().ifIncludeSymbol(indent.getGoodsName());
            if(ifNameFormatError){
                return "商品名不可包含特殊符号┭┮﹏┭┮";
            }
            boolean ifPriceFormatError = factory.getFormatService().formatService(String.valueOf(indent.getPrice()));
            if(ifPriceFormatError){
                return "价格仅支持整数";
            }
            boolean ifAmountFormatError = factory.getFormatService().formatService(String.valueOf(indent.getAmount()));
            if(ifAmountFormatError){
                return "商品仅支持整数";
            }

                factory.getDeleteOrChangeDao().deleteOrChange(null, 0, 0, null, null, true, indent);
            System.out.println(indent);
            }
        //完成订单
        if(FINISH_INDENT.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,"订单已完成","status"
                    ,false,null);
            //完成订单 经验值和积分加一
            factory.getUpdateDao().updateDao("user","exp","exp+1","integral","integral+1","username","\""+username+"\""); }
        //商家发货
        if(SELL.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,"商品在路上","status",false,null);
        }
        //完成订单后 买家留言
        if(BUYER_SEND_MESSAGE.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,message,"buyerMessage",false,null);
        }
        //买家留言后 商家回复
        if(SELLER_SEND_MESSAGE.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,message,"sellerMessage",false,null);
        }
        //用户删除留言板信息
        if(BUYER_DELETE_MESSAGE.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,"暂无","sellerMessage",false,null);
        }
        //商户删除留言板信息
        if(SELLER_DELETE_MESSAGE.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,"暂无","buyerMessage",false,null);
        }
        //用户添加商品入购物车
        if(SHOPPING_CAR.equals(message)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,"购物车",null,false,null);
        }
        //用户删除购物车信息
        if(DELETE_SHOPPING_CAR_INDENT.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",IF_DELETE,id,null,null,false,null);
        }
        //用户在购物车购买商品 修改购物车商品的购买数量 以及总价 使用积分的数量 实际付款
        if(BUY_GOODS_FROM_SHOPPING_CAR.equals(method)){
            boolean ifAmountFormatWrong = factory.getFormatService().formatService(String.valueOf(indent.getAmount()));
            boolean ifIntegralFormatWrong = factory.getFormatService().formatService(String.valueOf(indent.getUseIntegral()));
            if(!ifAmountFormatWrong && !ifIntegralFormatWrong) {
                int totalPrice = indent.getAmount() * indent.getPrice();
                factory.getDeleteOrChangeDao().deleteOrChange("indent", 0, id, "商家未接单", "status", false, null);
                factory.getDeleteOrChangeDao().deleteOrChange("indent", 0, id, String.valueOf(indent.getAmount()), "amount", false, null);
                factory.getDeleteOrChangeDao().deleteOrChange("indent", 0, id, String.valueOf(totalPrice), "totalPrice", false, null);
                factory.getDeleteOrChangeDao().deleteOrChange("indent", 0, id, String.valueOf(indent.getUseIntegral()), "useIntegral", false, null);
                factory.getDeleteOrChangeDao().deleteOrChange("indent", 0, id, String.valueOf(totalPrice - indent.getUseIntegral()), "actuallyPrice", false, null);
            }
            }
        //用户给好评
        if(GOOD_REPUTATION.equals(method)){
            String sellerName = factory.getQueryDao().queryDao("seller","indent","id",String.valueOf(id));
            factory.getUpdateDao().updateDao("user","reputationPoint","reputationPoint+1",null,null,"username","\""+sellerName+"\"");
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,"好评","reputation",false,null);
        }
        //用户给差评
        if(BAD_REPUTATION.equals(method)){
            String sellerName = factory.getQueryDao().queryDao("seller","indent","id",String.valueOf(id));
            factory.getUpdateDao().updateDao("user","reputationPoint","reputationPoint-1",null,null,"username","\""+sellerName+"\"");
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,"差评","reputation",false,null);
        }
    return "操作成功！";
    }
}
