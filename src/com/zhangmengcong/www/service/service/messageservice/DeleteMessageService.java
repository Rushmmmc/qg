package com.zhangmengcong.www.service.service.messageservice;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/6 23:22
 */
public interface DeleteMessageService {
    /**
     *
     * @param messageId 要删除消息的id
     * @param indentId 要修改的订单的id
     * @param ifSeller 选择卖家方删除消息或买家方删除消息
     * @return 提示信息
     */
    String deleteMessageServiceImpl(int messageId,int indentId,int ifSeller);

}
