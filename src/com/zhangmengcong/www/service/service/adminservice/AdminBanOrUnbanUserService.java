package com.zhangmengcong.www.service.service.adminservice;

/**
 * @author:zmc
 * @function: 用于管理员封禁
 * @date: 2020/5/1 18:21
 */
public interface AdminBanOrUnbanUserService {
    /** 封禁或解封用户
     *
     * @param ifBan 是否封禁 否 则使用解封
     * @param username 用户名
     * @param banReason 封禁理由
     * @return 是否成功
     */
    boolean adminBanOrUnbanUserService(int ifBan,String username,String banReason);
}
