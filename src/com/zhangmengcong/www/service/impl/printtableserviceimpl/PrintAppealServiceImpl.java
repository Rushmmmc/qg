package com.zhangmengcong.www.service.impl.printtableserviceimpl;

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
    public List<Appeal> printAppealServiceImpl(String username) {
        Factory factory = new Factory();
        if(username == null) {
            return factory.getAppealPrintDao().appealPrintDao(true, null);
        }else {
            return factory.getAppealPrintDao().appealPrintDao(false,username);
        }
        }
}
