package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.IfMailExistService;
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
        String tempMail = factory.getQueryDao().queryDao("mailaddress","user"
        ,"mailaddress","\""+mailAddress+"\"");
        return !"".equals(tempMail);
    }
}
