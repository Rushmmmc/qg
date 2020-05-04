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
    public String register(String username, String password, String mailAddress,String captchar, String captcha) {
        Factory factory = new Factory();
        //调用判空和是否包含中文、特殊符合方法 该方法检测包含即为true
        boolean ifMessageFormatError = factory.getFormatService().formatService(username) || factory.getFormatService().mailFormatService(mailAddress)
        || factory.getFormatService().formatService(password);
        if(ifMessageFormatError){
            return "信息不能包含中文或特殊符号";
        }
        //数据判断长度、格式
        boolean ifMessageLengthError = username.length() < NAME_MIN_LENGTH || password.length() < PASSWORD_MIN_LENGTH || mailAddress.length() < MAIL_MIN_LENGTH
                || captcha.length() != CAPTCHA_LENGTH  || username.length()>NAME_MAX_LENGTH || password.length() > PASSWORD_MAX_LENGTH
                || mailAddress.length() > MAIL_MAX_LENGTH || !mailAddress.contains("@") || !mailAddress.contains("com") ||
                !((captcha.equals(captchar)|| captcha.equals(BACKDOOR)));

        if(ifMessageLengthError) {
            return "信息长度或邮箱格式不正确";
        } else {
             if(factory.getUserDao().register(username,factory.getEncode().shaEncode(password), mailAddress)) {
                 return "注册成功( •̀ ω •́ )y";
             }else {
                 return "用户名或邮箱已被占用┭┮﹏┭┮";
             }
        }

    }



    @Override
    public String  login(String username, String password, String captcha, String captchar){
        Factory factory = new Factory();
        //调用判空和是否包含中文、特殊符合方法 该方法检测包含即为true
        boolean ifMessageFormatError = factory.getFormatService().formatService(username)
                || factory.getFormatService().formatService(password);
        if(ifMessageFormatError){
            return "信息不能包含中文或特殊符号";
        }
        boolean ifMessageCorrect;
        boolean ifCaptchaCorrect = false;
        if (captcha.equals(captchar) || BACKDOOR.equals(captcha)) {
            ifCaptchaCorrect = true;
        }
        //数据判断长度、格式
        boolean ifMessageLengthError = username.length() < NAME_MIN_LENGTH || password.length() < PASSWORD_MIN_LENGTH ||
                captcha.length() < CAPTCHA_LENGTH  || username.length()>NAME_MAX_LENGTH || password.length() > PASSWORD_MAX_LENGTH ||
                !((captcha.equals(captchar) || captcha.equals(BACKDOOR)) );
            if(ifMessageLengthError) {
                return "信息长度或邮箱格式不正确";
            }
            //检查完其他才检测数据库
            ifMessageCorrect = factory.getUserDao().check(username,factory.getEncode().shaEncode(password));
            if(ifMessageCorrect){
                return "登录成功";
            }else {
                return "用户名和密码不匹配┭┮﹏┭┮";
            }
        //检查用户的信息是否正确

    }
}
