package com.zhangmengcong.www.service.impl.userserviceimpl;

import com.zhangmengcong.www.service.service.userservice.AddExpAndIntegralService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/3 1:14
 */
public class AddExpAndIntegralServiceImpl implements AddExpAndIntegralService {
    @Override
    public void getAddExpAndIntegralService(String username) {
        Factory factory = new Factory();
        factory.getUserDao().addExpAndIntegral(username);
    }
}
