package com.zhangmengcong.www.service.impl.goodsserviceimpl;

import com.zhangmengcong.www.service.service.goodsservice.CheckUserIfTheGoodsSellerService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/11 10:08
 */
public class CheckUserIfTheGoodsSellerServiceImpl implements CheckUserIfTheGoodsSellerService {

    @Override
    public String checkUserIfTheGoodsSellerService(String username, int goodsId) {
        Factory factory = new Factory();
        String sellerName = factory.getQueryDao().queryDao("seller","goods",
                "id",String.valueOf(goodsId));
        //必须转换大小写
        if(sellerName.toLowerCase().equals(username.toLowerCase())){
            return "您不能购买自己的商品";
        }
    return "false";
    }
}
