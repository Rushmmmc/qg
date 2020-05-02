package com.zhangmengcong.www.service.service.userservice;

/**
 * @author:zmc
 * @function: 提供找回密码服务
 * @date: 2020/5/1 14:25
 */
public interface ForgetPasswordService {
    /**发送邮寄至忘记密码的用户的邮箱
     *
     * @param mailAddress 邮箱地址
     * @return 用于提示用成功与否的语句
     */
    String sendMail(String mailAddress);
}
