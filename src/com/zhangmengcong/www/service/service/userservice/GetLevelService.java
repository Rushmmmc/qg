package com.zhangmengcong.www.service.service.userservice;

/**
 * @author:zmc
 * @function: 获取用户等级
 * @date: 2020/4/29 20:53
 */
public interface GetLevelService {
    /** 获取用户等级的服务
     *
     * @param username 用户名
     * @return 用户等级
     */
    int getLevelService(String username);
}
