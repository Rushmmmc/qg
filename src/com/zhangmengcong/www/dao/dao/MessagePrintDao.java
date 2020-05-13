package com.zhangmengcong.www.dao.dao;

import com.zhangmengcong.www.po.Message;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印商家和用户的留言
 * @date: 2020/5/6 22:13
 */
public interface MessagePrintDao {
    /** 打印双方的留言
     *
     * @param indentId 订单号
     */
    List<Message> select(int indentId);
}
