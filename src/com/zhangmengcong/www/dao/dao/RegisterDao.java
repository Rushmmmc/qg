package com.zhangmengcong.www.dao.dao;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:18
 */
public interface RegisterDao {
    /**
     * 注册功能
     *
     * @param username    用户名
     * @param password    密码
     * @param mailAddress 电话
     * @return 是否成功
     */
    boolean register(String username, String password, String mailAddress);
}
