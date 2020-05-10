package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.service.service.indentservice.FinishIndentService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:29
 */
public class FinishIndentServiceImpl implements FinishIndentService {
    @Override
    public String finishIndent(int indentId, String username) {
        Factory factory = new Factory();
        if (factory.getFormatService().ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        factory.getDeleteOrChangeDao().deleteOrChange("indent", 0,
                indentId, "订单已完成", "status"
                , false, null);
        //完成订单 经验值和积分加一
        factory.getUpdateDao().updateDao("user", "exp", "exp+1",
                "integral", "integral+1", "username",
                "\"" + username + "\"");
        //查询订单的购买量
        int amount = Integer.parseInt(factory.getQueryDao().queryDao("amount",
                "indent","id",String.valueOf(indentId)));
        //查询订单的商品名
        String goodsName = factory.getQueryDao().queryDao("goodsName","indent","id",
                String.valueOf(indentId));
        //购买量自动增加
        factory.getUpdateDao().updateDao("goods","boughtAmount",
                "boughtAmount+"+ amount,"goodsName","\""+goodsName+"\"");
        return "订单已完成";
    }
}
