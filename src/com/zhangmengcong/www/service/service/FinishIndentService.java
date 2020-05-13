package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:29
 */
public interface FinishIndentService {
    /** 完成订单
     *
     * @param indentId 订单id
     * @param username 用户
     * @return 提示信息
     */
    String finishIndent(int indentId, String username);
}
