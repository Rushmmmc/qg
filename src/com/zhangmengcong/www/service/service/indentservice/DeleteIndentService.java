package com.zhangmengcong.www.service.service.indentservice;

import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function: 删除或修改订单
 * @date: 2020/5/2 12:20
 */
public interface DeleteIndentService {


    /** 删除订单
     *
     * @param ifSeller 是否商家
     * @param indentId 订单id
     * @return 提示信息
     */
    String deleteIndent(int ifSeller,int indentId);


    /** 用户删除订单
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String buyerDeleteIndent(int indentId);

    /** 商家删除订单
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String sellerDeleteIndent(int indentId);









}



