package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.service.service.indentservice.GiveUpIndentService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/9 0:34
 */
public class GiveUpIndentServiceImpl implements GiveUpIndentService {
    @Override
    public String giveUpIndent(int indentId) {
        Factory factory = new Factory();
        boolean ifIdFormatError = factory.getFormatService().formatService(String.valueOf(indentId));
        if(ifIdFormatError){
            return "id格式错误┭┮﹏┭┮";
        }

        //查询买家名
        String buyerName = factory.getQueryDao().queryDao("buyer","indent"
                ,"id","\""+indentId+"\"");

        //查询使用积分量
        int integral = Integer.parseInt(factory.getQueryDao().queryDao("useIntegral","indent"
                ,"id","\""+indentId+"\""));

        //返回积分
        factory.getUpdateDao().updateDao("user","integral",
                "integral+"+integral,"username","\""+buyerName+"\"");
        //商品存货回显
        int amount = Integer.parseInt(factory.getQueryDao().queryDao("amount","indent"
                ,"id","\""+indentId+"\""));
        factory.getUpdateDao().updateDao("goods","amount",
                "amount+"+amount,"id","\""+indentId+"\"");

        //在买家方删除订单
        factory.getUpdateDao().updateDao("indent","ifBuyerDelete",
                "1","id","\""+indentId+"\"");
        //在卖家方显示订单已被取消
        factory.getUpdateDao().updateDao("indent","status",
               "'买家已取消'","id","\""+indentId+"\"");
        return "订单已取消,返回积分"+integral;

    }
}
