package com.zhangmengcong.www.service.service.indentservice;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/9 0:34
 */
public interface GiveUpIndentService {
    /** 商家还未接单 用户放弃订单
     *
     * @param indentId 订单id
     * @return 提示信息
     */
   String giveUpIndent(int indentId);
}
