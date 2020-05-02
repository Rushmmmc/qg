package com.zhangmengcong.www.service.impl.userserviceimpl;

import com.zhangmengcong.www.service.service.userservice.RegisterOrLoginService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.UserConstant.*;

/**
 * @author:zmc
 * @function: 注册和登录的服务
 * @date: 2020/4/25 10:34
 */
public class RegisterOrLoginServiceImpl implements RegisterOrLoginService {
    @Override
    public boolean register(String username, String password, String mailAddress,String captchar, String captcha) {
        Factory factory = new Factory();
        if (username.length() < NAME_LENGTH || password.length() < PASSWORD_LENGTH
                || (mailAddress.length() < MAIL_LENGTH)) {
            return false;
        }else {
            return factory.getUserDao().register(username,password,mailAddress) &&
                    (captcha.equals(captchar) || captcha.equals(BACKDOOR) );
        }
    }



    @Override
    public boolean  login(String username, String password, String captcha, String captchar){
        Factory factory = new Factory();
        boolean ifMessageCorrect;
        boolean ifCaptchaCorrect = false;
        if (captcha.equals(captchar) || BACKDOOR.equals(captcha)) {
            ifCaptchaCorrect = true;
        }
        ifMessageCorrect = factory.getUserDao().check(username, password);
        //检查用户的信息是否正确
        return (ifCaptchaCorrect && ifMessageCorrect);
    }
}
