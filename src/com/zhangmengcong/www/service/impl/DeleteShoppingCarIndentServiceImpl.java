package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.DeleteShoppingCarIndentService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_DELETE;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:40
 */
public class DeleteShoppingCarIndentServiceImpl implements DeleteShoppingCarIndentService {
    @Override
    public String deleteShoppingCarIndent(int indentId) {
        Factory factory = new Factory();
        if (factory.getFormatService().ifIdFormatWrong(String.valueOf(indentId))) {
            return "订单id格式错误";
        }
        factory.getDeleteOrChangeDao().deleteOrChange("indent", IF_DELETE,
                indentId, null, null, false, null);
        //用户删除购物车信息
        return "删除成功！";
    }
}
