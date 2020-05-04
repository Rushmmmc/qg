package com.zhangmengcong.www.service.impl.userserviceimpl;

import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.service.service.userservice.ChangeMessageService;
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
           boolean ifMessageFormatError = factory.getFormatService().formatService(username) || factory.getFormatService().mailFormatService(mailAddress)
                   || factory.getFormatService().formatService(password);
           if(ifMessageFormatError){
               return "信息不能包含中文或特殊符号";
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


            //检测完再加密
            user.setPassword(factory.getEncode().shaEncode(password));
            if(factory.getUserDao().change(user,username)){
                return "修改成功( •̀ ω •́ )y";
            }else {
                return "用户名或邮箱已被占用┭┮﹏┭┮";
            }
        }
}
