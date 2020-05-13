package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.service.service.ChangeMessageService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.UserConstant.*;


/**
 * @author:zmc
 * @function: 修改用户信息的服务
 * @date: 2020/4/29 20:23
 */
public class ChangeMessageServiceImpl implements ChangeMessageService {
       @Override
       public String getChangeMessageServiceImpl(User user, String username) {
           Factory factory = new Factory();
           String password = user.getPassword();
           String mailAddress = user.getMailAddress();

           //调用判空和是否包含中文、特殊符合方法 该方法检测包含即为true
           boolean ifUsernameFormatWrong = factory.getFormatService().formatService(user.getUsername());
           if(ifUsernameFormatWrong){
               return "新用户名不可为空、包含特殊符号";
           }

           boolean ifPasswordFormatWrong = factory.getFormatService().formatService(password);
           if(ifPasswordFormatWrong){
               return "新密码不可为空、包含特殊符号";
           }

           boolean ifMailAddressWrong = factory.getFormatService().mailFormatService(mailAddress);
           if(ifMailAddressWrong){
               return "新邮箱不可包含中文和特殊符号！,不可包含多个@";
           }
           //数据判断长度、格式
           boolean ifMessageLengthError = username.length() < NAME_MIN_LENGTH || password.length() < PASSWORD_MIN_LENGTH || mailAddress.length() < MAIL_MIN_LENGTH
                   || username.length()>NAME_MAX_LENGTH || password.length() > PASSWORD_MAX_LENGTH
                   || mailAddress.length() > MAIL_MAX_LENGTH || !mailAddress.contains("@") || !mailAddress.contains("com");
           if(ifMessageLengthError) {
               return "信息长度或邮箱格式不正确";
           }

           //检测用户邮箱的@和.com是否重复出现
           String tempMail = mailAddress;
           tempMail = tempMail.replace("@","");
           tempMail = tempMail.replace("com","");
           if(tempMail.length() != mailAddress.length() - MINUS_LENGTH){
               return "请不要出现多个@或多个com┭┮﹏┭┮";
           }
           //检测用户名与邮箱是否存在
           String tempUsername = factory.getQueryDao().queryDao("username","user","username","\""+user.getUsername()+"\"");
           //如果用户想修改的用户名已存在并不为现用户名
           if(!"".equals(tempUsername) && !tempUsername.equals(username)){
                return "该用户名已存在,换一个吧┭┮﹏┭┮";
            }
           String nowMail =  factory.getQueryDao().queryDao("mailaddress","user","username","\""+username+"\"");
           String tempMail2 = factory.getQueryDao().queryDao("mailaddress","user","mailaddress","\""+mailAddress+"\"");
           //如果用户想修改的邮箱已存在并不为现用户名
           if(!"".equals(tempMail2) && !nowMail.equals(tempMail2)){
               return "该邮箱已存在,换一个吧┭┮﹏┭┮";
           }
            //检测完再加密
            user.setPassword(factory.getEncode().shaEncode(password));
            if(factory.getUserChangeMessageDao().change(user,username)){
                return "修改成功( •̀ ω •́ )y";
            }else {
                return "用户名或邮箱已被占用┭┮﹏┭┮";
            }
        }
}
