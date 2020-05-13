package com.zhangmengcong.www.dao.dao;

import com.zhangmengcong.www.po.Message;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 15:27
 */
public interface AddNewMessageDao {
    /** 在订单留言版中添加新的留言
     *
     * @param message 消息对象
     * @return 是否成功
     */
    boolean addNewMessageDao(Message message);
}
