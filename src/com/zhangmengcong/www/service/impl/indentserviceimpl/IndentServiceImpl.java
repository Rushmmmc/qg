package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.indentservice.IndentService;
import com.zhangmengcong.www.util.Factory;


import java.util.List;

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
    public void indentSelectMethod(String method,int id,Indent indent,String message,String username) {
        Factory factory = new Factory();
        //删除订单
        if(DELETE_INDENT.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent", IF_DELETE, id, null, null, false, null);
        }
        //改变订单商品个数 金额等信息
        if(CHANGE_INDENT.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange(null, 0, 0, null, null, true, indent);
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
        //用户在购物车购买商品 修改购物车商品的购买数量 以及总价
        if(BUY_GOODS_FROM_SHOPPING_CAR.equals(method)){
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,"商家未接单","status",false,null);
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,String.valueOf(indent.getAmount()),"amount",false,null);
            factory.getDeleteOrChangeDao().deleteOrChange("indent",0,id,String.valueOf(indent.getAmount()*indent.getPrice()),"totalPrice",false,null);
        }
        //用户给好评
        if(GOOD_REPUTATION.equals(message)){
            String sellerName = factory.getQueryDao().queryDao("indent","seller","id",String.valueOf(id));
            factory.getUpdateDao().updateDao("user","reputationPoint","reputationPoint+1",null,null,"username",sellerName);
        }
        //用户给差评
        if(BAD_REPUTATION.equals(message)){
            String sellerName = factory.getQueryDao().queryDao("indent","seller","id",String.valueOf(id));
            factory.getUpdateDao().updateDao("user","reputationPoint","reputationPoint-1",null,null,"username",sellerName);
        }

    }




}
