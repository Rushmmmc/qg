package com.zhangmengcong.www.dao.dao;



/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:21
 */
public interface CheckNameAndPasswordDao {
    /**
     * 检查用户密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     * @return 布尔值
     */
    boolean check(String username, String password);

}
