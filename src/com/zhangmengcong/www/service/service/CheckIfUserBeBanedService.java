package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 19:27
 */
public interface CheckIfUserBeBanedService {
   /** 检测用户是否被封禁
    *
    * @param username 用户名
    * @return 是否被封禁
    */
   boolean checkIfUserBeBanedService(String username);

}
