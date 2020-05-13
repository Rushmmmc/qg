package com.zhangmengcong.www.dao.dao;

import com.zhangmengcong.www.po.User;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:27
 */
public interface UserChangeMessageDao {
    /**
     * 修改用户信息
     *
     * @param username 用户的原用户名
     * @param user     包含用户各种信息的对象
     * @return /给用户改密码
     */
    boolean change(User user, String username);
}
