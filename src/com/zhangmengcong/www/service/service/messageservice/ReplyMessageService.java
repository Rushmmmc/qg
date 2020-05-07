package com.zhangmengcong.www.service.service.messageservice;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/6 23:14
 */
public interface ReplyMessageService {
    /**
     *
     * @param ifSeller 是否卖家
     * @param message 回复消息
     * @param id 回复的消息的id
     * @param indentId
     * @return 提示信息
     */
    String replyMessageService(int ifSeller,String message,int id,int indentId);
}
