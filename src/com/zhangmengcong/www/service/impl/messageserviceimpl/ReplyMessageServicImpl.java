package com.zhangmengcong.www.service.impl.messageserviceimpl;

import com.zhangmengcong.www.service.service.messageservice.ReplyMessageService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/6 23:16
 */
public class ReplyMessageServicImpl implements ReplyMessageService {
    @Override
    public String replyMessageService(int ifSeller, String message,int id,int indentId) {
        Factory factory = new Factory();

        if(ifSeller != 0 && ifSeller != 1){
            return "仅支持商家或用户回复";
        }
        boolean ifMessageFormatWrong = factory.getFormatService().ifIncludeSymbol(message);
        if(ifMessageFormatWrong){
            return "消息不支持特殊符号┭┮﹏┭┮";
        }
        //检测是否存在该留言id
        boolean ifMessageIdExist = factory.getCheckIfReplyIdExistDao().checkIfReplyIdExist(id,indentId);
        if(!ifMessageIdExist){
            return "该留言ID不存在";
        }
        if(ifSeller == 1) {
            factory.getUpdateDao().updateDao("message",
                    "sellerMessage","\""+message+"\"",null,null,"id",String.valueOf(id));
            factory.getUpdateDao().updateDao("indent",
                    "sellerMessage","\"已留言\"",null,null,"id",String.valueOf(indentId));
        }
        if(ifSeller == 0) {
            factory.getUpdateDao().updateDao("message","buyerMessage","\""+message+"\"",null,null,"id",String.valueOf(id));
            factory.getUpdateDao().updateDao("indent",
                    "buyerMessage","\"已留言\"",null,null,"id",String.valueOf(indentId));
        }
        return "留言成功！";
        }
}
