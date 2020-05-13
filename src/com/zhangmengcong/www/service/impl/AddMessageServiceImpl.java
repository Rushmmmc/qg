package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.po.Message;
import com.zhangmengcong.www.service.service.AddMessageService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_DELETE;

/**
 * @author:zmc
 * @function: 关于留言板的服务
 * @date: 2020/5/6 22:53
 */
public class AddMessageServiceImpl implements AddMessageService {
    @Override
    public String deleteMessageService(int id) {
        Factory factory = new Factory();
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(id));
        if(!ifIdFormatWrong) {
            factory.getDeleteOrChangeDao().deleteOrChange("message", IF_DELETE, id, null, null, false, null);
        return "消息删除成功( •̀ ω •́ )y";
        }
        return "id格式不正确┭┮﹏┭┮";
    }

    @Override
    public String addMessageService(Indent indent, Message message) {
        Factory factory = new Factory();
        message.setBuyerName(indent.getBuyer());
        message.setSellerName(indent.getSeller());
        message.setIndentId(indent.getId());
        boolean ifbuyerNameWrong = factory.getFormatService().formatService(message.getBuyerName());
        if(ifbuyerNameWrong){
            return "买家名格式错误┭┮﹏┭┮";
        }
        boolean ifSellerNameWrong = factory.getFormatService().formatService(message.getSellerName());
        if(ifSellerNameWrong){
            return "卖家名格式错误┭┮﹏┭┮";
        }
        if(message.getBuyerMessage() != null) {
            boolean ifBuyerMessageWrong = factory.getFormatService().ifIncludeSymbol(message.getBuyerMessage());
            if (ifBuyerMessageWrong) {
                return "消息不可包含特殊符号┭┮﹏┭┮";
            }
        }
        if(message.getSellerMessage() != null) {
            boolean ifSellerMessageWrong = factory.getFormatService().ifIncludeSymbol(message.getSellerMessage());
            if (ifSellerMessageWrong) {
                return "消息不可包含特殊符号┭┮﹏┭┮";
            }
        }
        if(message.getBuyerMessage() != null ) {
            if (factory.getAddNewMessageDao().addNewMessageDao(message)) {
                factory.getUpdateDao().updateDao("indent", "buyerMessage", "\"已留言\"", null, null, "id", String.valueOf(indent.getId()));
                return "留言成功！";
            }
        }
        if(message.getSellerMessage() != null){
            if (factory.getAddNewMessageDao().addNewMessageDao(message)) {
                factory.getUpdateDao().updateDao("indent", "sellerMessage", "\"已留言\"", null, null, "id", String.valueOf(indent.getId()));
                return "留言成功！";
            }
        }
            return "留言失败！";

    }

}
