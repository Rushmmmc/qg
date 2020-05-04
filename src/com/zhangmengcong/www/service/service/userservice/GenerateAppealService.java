package com.zhangmengcong.www.service.service.userservice;

import com.zhangmengcong.www.po.Appeal;

/**
 * @author:zmc
 * @function: 用户投诉 生成申诉
 * @date: 2020/5/3 22:59
 */
public interface GenerateAppealService {
    /** 用户投诉 生成申诉
     *
     * @param appeal 封装好的appeal对象
     * @return 提示信息 如提示申诉成功
     */
    String generateAppealService(Appeal appeal);
}
