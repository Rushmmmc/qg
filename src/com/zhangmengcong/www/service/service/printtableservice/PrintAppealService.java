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
     *
     * @return 返回list
     */
    List<Appeal> printAppealServiceImpl();
}
