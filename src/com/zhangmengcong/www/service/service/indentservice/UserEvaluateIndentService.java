package com.zhangmengcong.www.service.service.indentservice;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:34
 */
public interface UserEvaluateIndentService {
    /**
     *
     * @param evaluate 评价
     * @param indentId 订单id
     * @return 提示信息
     */
    String userEvaluateIndent(String evaluate,int indentId);
}
