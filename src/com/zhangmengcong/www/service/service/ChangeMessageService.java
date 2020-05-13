package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.po.User;

/**
 * @author:zmc
 * @function: 修改用户信息
 * @date: 2020/4/29 20:23
 */
public interface ChangeMessageService {
    /** 修改用户信息
     * @param user  包含用户各种信息的对象
     * @param  username 用户的原用户名
     * @return 返回提示信息
     */
    String getChangeMessageServiceImpl(User user, String username);
}
