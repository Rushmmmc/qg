package com.zhangmengcong.www.dao.dao.UserDao;


import com.zhangmengcong.www.po.User;

/**
 * @author zmc
 * @function:  用户的数据处理
 * @date 2020/4/14 1:48
 */
public interface UserDao {
    /** 注册功能
     *
     * @param username 用户名
     * @param password 密码
     * @param mailAddress 电话
     * @return 是否成功
     */
    boolean register(String username, String password, String mailAddress);

    /** 检查用户密码是否正确
     *
     * @param username 用户名
     * @param password 密码
     * @return 布尔值
     */
    boolean check(String username, String password);

    /** 获取用户等级
     *
     * @param username 用户名
     * @return 等级
     */
    int getLevel(String username);

    /** 修改用户信息
     * @param  username 用户的原用户名
     * @param user  包含用户各种信息的对象
     * @return /给用户改密码
     */
       boolean change(User user, String username);


    /** 检查用户用于找回密码的邮箱是否正确
     *
     * @param mailAddress 邮箱地址
     * @return 是否存在该用户
     */
    boolean checkMail(String mailAddress);

    /** 获取邮箱地址对应的用户名
     *
     * @param address 邮箱地址
     * @return 用户名
     */
    String getusername(String address);

    /**检查用户是否被封禁
     *
     * @param username 用户名
     * @return 是否被封禁
     */
    boolean selectUserIfBan(String username);

    }



















