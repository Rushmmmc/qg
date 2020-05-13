package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function: 匹配用户等级
 * @date: 2020/4/23 14:44
 */
public interface EstimateStatusService {
    /** 匹配用户等级信息
     *
     * @param level 等级
     * @return 传递的提示等级字符
     */
    String estimateStatus(int level);
}
