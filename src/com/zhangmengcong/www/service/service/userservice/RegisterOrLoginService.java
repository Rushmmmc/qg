package com.zhangmengcong.www.service.service.userservice;

/**
 * @author:zmc
 * @function: 用户的登录和注册处理
 * @date: 2020/4/21 18:02
 */
public interface RegisterOrLoginService {
    /** 用于用户注册
     *
     * @param username 用户名
     * @param password 密码
     * @param mailAddress 电话
     * @param captchar 实际验证码
     * @param captcha 用户输入的验证码
     * @return 用于提示用户的信息
     */
     String register(String username, String password, String mailAddress, String captchar, String captcha);


    /** 用于用户登录
     *
     * @param username 用户名
     * @param password 密码
     * @param captchar 实际验证码
     * @param captcha 用户输入的验证码
     * @return 提示用户的信息
     */
     String  login(String username, String password, String captcha, String captchar);
}