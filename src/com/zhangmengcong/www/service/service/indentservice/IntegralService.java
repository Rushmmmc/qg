package com.zhangmengcong.www.service.service.indentservice;

/**
 * @author:zmc
 * @function: 使用积分抵现金
 * @date: 2020/5/3 14:29
 */
public interface IntegralService {
    /** 使用积分 需检测用户积分是否足够
     *
     * @param integral 使用积分数
     * @param username 用户名
     * @return 返回用户的积分是否足够
     */
   boolean useIntegralService(int integral,String username);
}
