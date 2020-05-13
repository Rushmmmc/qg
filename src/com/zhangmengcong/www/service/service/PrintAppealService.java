package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.po.Appeal;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印申诉信息
 * @date: 2020/5/3 19:15
 */
public interface PrintAppealService {
    /** 打印申诉信息
     * @param level  根据用户等级打印 若为管理员则管理全部信息
     * @param username 筛选出该用户的申诉信息 为null则为管理员打印全部
     * @return 返回list
     */
    List<Appeal> printAppealServiceImpl(int level,String username);
}
