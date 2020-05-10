package com.zhangmengcong.www.dao.dao.message;

import com.zhangmengcong.www.po.Message;

/**
 * @author:zmc
 * @function: 新增留言
 * @date: 2020/5/6 23:04
 */
public interface CheckIfReplyIdExistDao {


    /** 检测回复时 用户输入的消息id是否存在
     *
     * @param messageId 消息id
     * @param indentId 订单id
     * @return 是否存在
     */
    boolean checkIfReplyIdExist(int messageId,int indentId);
}
