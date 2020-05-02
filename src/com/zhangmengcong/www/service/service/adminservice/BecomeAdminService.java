package com.zhangmengcong.www.service.service.adminservice;

/**
 * @author:zmc
 * @function: 成为管理员
 * @date: 2020/4/29 22:56
 */
public interface BecomeAdminService {
    /** 成为管理员
     *
     * @param level 等级
     * @param username 用户名
     * @return 修改用户等级为管理员 便于测试管理员功能
     */
    boolean becomeAdminServiceImpl(int level,String username);
}
