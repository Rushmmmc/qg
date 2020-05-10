package com.zhangmengcong.www.service.service.printtableservice;

import com.zhangmengcong.www.po.DividePageParameter;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.PageBean;

/**
 * @author:zmc
 * @function: 打印表格
 * @date: 2020/4/29 15:55
 */
public interface PrintTableByPageService {

    /** 分页的封装
     * @param dividePageParameter 分页信息的封装对象
     * @return 分页信息的封装对象
     */
    PageBean<Goods> byPage(DividePageParameter dividePageParameter);


}
