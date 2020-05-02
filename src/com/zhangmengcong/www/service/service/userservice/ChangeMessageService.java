package com.zhangmengcong.www.service.service.userservice;

import com.zhangmengcong.www.po.User;

/**
 * @author:zmc
 * @function: 修改用户信息
 * @date: 2020/4/29 20:23
 */
public interface ChangeMessageService {
    /** 修改用户信息
     * @param  username 用户的原用户名
     * @param user  包含用户各种信息的对象
     * @return /给用户改密码
     */
    boolean getChangeMessageServiceImpl(User user,String username);
}
