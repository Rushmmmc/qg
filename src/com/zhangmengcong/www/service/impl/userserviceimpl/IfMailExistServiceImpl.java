package com.zhangmengcong.www.service.impl.userserviceimpl;

import com.zhangmengcong.www.service.service.userservice.IfMailExistService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 14:47
 */
public class IfMailExistServiceImpl implements IfMailExistService {
    @Override
    public boolean ifMailExistService(String mailAddress){
        Factory factory = new Factory();
        return factory.getUserDao().checkMail(mailAddress);
    }
}
