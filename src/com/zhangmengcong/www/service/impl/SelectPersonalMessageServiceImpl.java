package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.service.service.SelectPersonalMessageService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:04
 */
public class SelectPersonalMessageServiceImpl implements SelectPersonalMessageService {
    @Override
    public List<User> selectPersonalMessage(String username){
        Factory factory = new Factory();
        return factory.getSelectPersonalMessageDao().selectPersonalMessage(username);
    }
}
