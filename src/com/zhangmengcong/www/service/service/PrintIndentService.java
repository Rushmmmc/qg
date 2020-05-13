package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.po.Indent;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 20:21
 */
public interface PrintIndentService {
   /** 打印订单
    *
    * @param id 订单id 不为null才使用
    * @param username 用户名
    * @param ifSeller 是否卖家
    * @param ifShoppingCar 是否使用购物车
    * @return 订单list
    */
   List<Indent> printIndentService(int id,String username,int ifSeller,int ifShoppingCar);
}
