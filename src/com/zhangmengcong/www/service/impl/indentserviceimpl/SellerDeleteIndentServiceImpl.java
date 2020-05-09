package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.service.service.indentservice.SellerDeleteIndentService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/9 9:13
 */
public class SellerDeleteIndentServiceImpl implements SellerDeleteIndentService {
    @Override
    public String sellerDeleteIndentService(int indentId) {
        Factory factory = new Factory();
        boolean ifIdFormatError = factory.getFormatService().formatService(String.valueOf(indentId));
        if(ifIdFormatError){
            return "id格式错误┭┮﹏┭┮";
        }
        //在卖家方删除订单
        factory.getUpdateDao().updateDao("indent","ifSellerDelete",
                "1","id","\""+indentId+"\"");
        return "已删除订单！";
    }
}
