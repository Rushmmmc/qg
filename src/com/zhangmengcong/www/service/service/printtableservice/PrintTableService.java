package com.zhangmengcong.www.service.service.printtableservice;

import com.zhangmengcong.www.po.DividePageParameter;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.PageBean;
import com.zhangmengcong.www.po.User;

import java.util.List;

/**
 * @author:zmc
 * @function: 打印表格
 * @date: 2020/4/29 15:55
 */
public interface PrintTableService {

    /** 分页的封装
     * @param dividePageParameter 分页信息的封装对象
     * @return 分页信息的封装对象
     */
    PageBean<Goods> byPage(DividePageParameter dividePageParameter);

    /** 打印个人信息 用于修改信息页面
     *
     * @param username 用户名
     * @return 打印个人信息对象
     */
    List<User> selectPersonalMessage(String username);
}
