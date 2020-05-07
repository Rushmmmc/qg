package com.zhangmengcong.www.dao.dao.message;

import com.zhangmengcong.www.po.Message;

/**
 * @author:zmc
 * @function: 新增留言
 * @date: 2020/5/6 23:04
 */
public interface AddMessageDao {
    /**
     *
     * @param message 消息对象
     * @return 是否成功
     */
    boolean addNewMessageDao(Message message);
}
