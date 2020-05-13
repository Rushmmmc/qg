package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.po.Message;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印留言板信息
 * @date: 2020/5/6 22:41
 */
public interface PrintMessageService {
    List<Message> printMessageService(int id);
}
