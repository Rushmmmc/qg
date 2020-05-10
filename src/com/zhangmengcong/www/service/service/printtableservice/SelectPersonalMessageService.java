package com.zhangmengcong.www.service.service.printtableservice;

import com.zhangmengcong.www.po.User;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:04
 */
public interface SelectPersonalMessageService {
    /** 打印个人信息 用于修改信息页面
     *
     * @param username 用户名
     * @return 打印个人信息对象
     */
    List<User> selectPersonalMessage(String username);
}
