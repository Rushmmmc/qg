package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Message;
import com.zhangmengcong.www.service.service.PrintMessageService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

/**
 * @author:zmc
 * @function:  打印留言板信息
 * @date: 2020/5/6 22:41
 */
public class PrintMessageServiceImpl implements PrintMessageService {
    @Override
    public List<Message> printMessageService(int id) {
        Factory factory = new Factory();
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(id));
        if(!ifIdFormatWrong){
            return factory.getMessagePrintDao().select(id);
        }
    return null;
    }
}
