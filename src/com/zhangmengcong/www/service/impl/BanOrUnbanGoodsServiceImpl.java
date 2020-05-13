package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.BanOrUnbanGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_BAN;

/**
 * @author:zmc
 * @function: 封禁/解封商品
 * @date: 2020/5/13 19:14
 */
public class BanOrUnbanGoodsServiceImpl implements BanOrUnbanGoodsService {

    @Override
    public String banOrUnbanGoodsService(int ifBan, int goodsId) {
        Factory factory = new Factory();
        //检测商品id
        factory.getFormatService().formatService(String.valueOf(goodsId));

        boolean ifIdFormatWrong = factory.getFormatService().ifIdFormatWrong(String.valueOf(goodsId));
        if(ifIdFormatWrong){
            return "商品id格式有误！";
        }

        if(ifBan == IF_BAN){
            factory.getUpdateDao().updateDao("goods","status","'已封禁'","id",
                    String.valueOf(goodsId));
            return "商品已被禁止售卖！";
        }
        if(ifBan == 0){
            factory.getUpdateDao().updateDao("goods","status","'已审核'","id",
                    String.valueOf(goodsId));
            return "商品已解封";
        }
        return "仅支持解封与封禁";
    }
}
