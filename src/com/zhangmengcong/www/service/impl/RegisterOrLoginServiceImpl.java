package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.RegisterOrLoginService;
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
        String formatMessage = checkMesssageFormat(username,password,captcha,mailAddress);
        //不返回true即格式错误 返回对应的提示信息
        if(!formatMessage.contains(TRUE)){
            return formatMessage;
        }
        //判断数据格式
        if(!mailAddress.contains(MAIL_SYMBOL1) || !mailAddress.contains(MAIL_SYMBOL2)){
            return "邮箱必须包含@和.com";
        }
        String tempMail = mailAddress.replace("@","");
        tempMail = tempMail.replace("com","");
        //检测是否包含多个@或多个com
        if(mailAddress.length() != tempMail.length() + COM_LENGTH){
            return "邮箱不可包含多个@或多个com┭┮﹏┭┮";
        }
        //检测用户名是否被占用
        String tempUsername = factory.getQueryDao().queryDao("username","user"
        ,"username","\""+username+"\"");
        if(!"".equals(tempUsername)){
            return "用户名已被占用！";
        }
        //判断数据长度 返回true即长度无误
        String lengthMessage = checkMessageLengthAndCheckCaptcha(username,password,captcha,captchar,mailAddress);
        if(lengthMessage.contains(TRUE)) {
            if (factory.getRegisterDao().register(username, factory.getEncode().shaEncode(password), mailAddress)) {
                return "true";
            } else {
                return "邮箱已被占用┭┮﹏┭┮";
            }
        }else {
            return lengthMessage;
        }
    }


    @Override
    public String  login(String username, String password, String captcha, String captchar){
        Factory factory = new Factory();
        String lengthMessage = checkMessageLengthAndCheckCaptcha(username,password,captcha,captchar,null);
        //不包含true即为数据长度或验证码出错 返回对应的提示信息
        if(!lengthMessage.contains(TRUE)){
            return lengthMessage;
        }
        String formatMessage = checkMesssageFormat(username,password,captcha,null);
        //不包含true即为数据格式 返回对应的提示信息
        if(!formatMessage.contains(TRUE)){
            return formatMessage;
        }
        //数据判断长度、格式
        //检查完其他才检测数据库
            if(factory.getCheckNameAndPasswordDao().check(username,factory.getEncode().shaEncode(password))){
                return "登录成功";
            }else {
                return "用户名和密码不匹配┭┮﹏┭┮";
            }
        //检查用户的信息是否正确
    }

    @Override
    public String checkMessageLengthAndCheckCaptcha(String username, String password,String captcha,String captchar,String mailAddress){
        if(!((captcha.equals(captchar) || captcha.equals(BACKDOOR)))){
            return "验证码错误┭┮﹏┭┮";
        }
        if(username.length() < NAME_MIN_LENGTH || username.length()>NAME_MAX_LENGTH){
            return "用户名长度有误┭┮﹏┭┮";
        }
        if(password.length() < PASSWORD_MIN_LENGTH || password.length() > PASSWORD_MAX_LENGTH){
            return "密码长度有误┭┮﹏┭┮";
        }
        if(captcha.length() != CAPTCHA_LENGTH){
            return "验证码长度有误┭┮﹏┭┮";
        }
        if(mailAddress != null) {
            if (mailAddress.length() < MAIL_MIN_LENGTH || mailAddress.length() > MAIL_MAX_LENGTH) {
                return "邮箱长度有误呜呜";
            }
        }
    return "true";
    }

    @Override
    public String checkMesssageFormat(String username, String password, String captcha, String mailAddress){
    Factory factory = new Factory();
        //调用判空和是否包含中文、特殊符合方法 该方法检测包含即为true
        if(factory.getFormatService().formatService(username)){
            return "用户名不可包含中文或特殊符号！";
        }
        if(factory.getFormatService().formatService(password)){
            return "密码不可包含中文或特殊符号！";
        }
        if(mailAddress != null) {
            if (factory.getFormatService().mailFormatService(mailAddress)) {
                return "邮箱不可包含除@和.之外的特殊符号！";
            }
        }
        if (factory.getFormatService().formatService(captcha)) {
            return "验证码不可包含中文或特殊符号！";
        }

    return "true";
    }
}
