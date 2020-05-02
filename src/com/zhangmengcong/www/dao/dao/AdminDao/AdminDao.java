package com.zhangmengcong.www.dao.dao.AdminDao;


/**
 * @author zmc
 * @function: 管理员相关的数据处理
 * @date 2020/4/14 13:11
 */
public interface AdminDao {

    /** 一键成为管理员
     *
     * @param level 等级
     * @param username 用户名
     * @return boolean
     */
     boolean becomeAdmin(int level, String username);

    /** 封禁或解封用户
     *
     * @param ifBan 是否使用封禁 否则即是解封
     * @param username 用户名
     * @return 是否成功
     */
     boolean banOrUnbanUser(int ifBan,String username,String banreason);
 }
































