package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.DeleteMessageService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/6 23:22
 */
public class DeleteMessageServiceImpl implements DeleteMessageService {

   @Override
   public String deleteMessageServiceImpl(int messageId,int indentId,int ifSeller){
        Factory factory = new Factory();
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(messageId));
        if(ifIdFormatWrong){
            return "id仅支持整数┭┮﹏┭┮";
        }else {
            if(ifSeller == IF_SELLER) {
                factory.getUpdateDao().updateDao("message","sellerDelete","\"1\"","id","\""+messageId+"\"");
                factory.getUpdateDao().updateDao("indent","buyerMessage","\"暂无\"","id",String.valueOf(indentId));
                return "删除成功！";
            }
            if(ifSeller == 0) {
                factory.getUpdateDao().updateDao("message","buyerDelete","\"1\"","id","\""+messageId+"\"");
                factory.getUpdateDao().updateDao("indent","sellerMessage","\"暂无\"","id",String.valueOf(indentId));
                return "删除成功！";
            }
        }
   return "仅支持卖家或买家删除消息功能";
   }
}
