package com.zhangmengcong.www.service.service.indentservice;

import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function: 删除或修改订单
 * @date: 2020/5/2 12:20
 */
public interface IndentService {
    /** 根据method选择调用的方法 简化Controller 内含修改 删 完成订单
     *
     * @param method 选择的方法
     * @param id 订单的id
     * @param indent 订单对象 修改完整订单时使用
     * @param message 商家用户互相留言时传入
     */
    void indentSelectMethod(String method,int id,Indent indent,String message,String username);

}
