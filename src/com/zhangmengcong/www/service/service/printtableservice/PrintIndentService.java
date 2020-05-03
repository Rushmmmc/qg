package com.zhangmengcong.www.service.service.printtableservice;

import com.zhangmengcong.www.po.Indent;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 20:21
 */
public interface PrintIndentService {
   List<Indent> printIndentService(String username,int ifSeller,int ifShoppingCar);
}
