package com.zhangmengcong.www.service.service.printtableservice;

import com.zhangmengcong.www.po.Appeal;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印申诉信息
 * @date: 2020/5/3 19:15
 */
public interface PrintAppealService {
    /** 打印申诉信息
     * @param username 筛选出该用户的申诉信息 为null则为管理员打印全部
     * @return 返回list
     */
    List<Appeal> printAppealServiceImpl(String username);
}
