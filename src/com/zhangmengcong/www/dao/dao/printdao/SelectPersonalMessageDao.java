package com.zhangmengcong.www.dao.dao.printdao;

import com.zhangmengcong.www.po.User;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:11
 */
public interface SelectPersonalMessageDao {
    /** 给用户展示个人信息
     *
     * @param username 用户名
     * @return list表单
     */
    List<User> selectPersonalMessage(String username);
}
