package com.zhangmengcong.www.service.impl.printtableserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.printtableservice.PrintIndentService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 20:21
 */
public class PrintIndentServiceImpl implements PrintIndentService {
    @Override
    public List<Indent> printIndentService(String username,int ifSeller) {
        Factory factory = new Factory();
        return factory.getIndentPrintDao().selectPersonalIndent(username,ifSeller);
    }
}
