package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Appeal;
import com.zhangmengcong.www.service.service.printtableservice.PrintAppealService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印申诉信息
 * @date: 2020/5/3 19:15
 */
public class PrintAppealServiceImpl implements PrintAppealService {

    @Override
    public List<Appeal> printAppealServiceImpl() {
        Factory factory = new Factory();
        return factory.getAppealPrintDao().appealPrintDao();
    }
}
