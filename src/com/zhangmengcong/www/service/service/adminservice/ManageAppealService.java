package com.zhangmengcong.www.service.service.adminservice;

/**
 * @author:zmc
 * @function: 扣除被投诉商家的信誉分
 * @date: 2020/5/3 21:06
 */
public interface ManageAppealService {
    /** 扣除被投诉商家的信誉分
     * @param id 订单id
     * @param username 可怜的商家
     */
    void minusSellerReputationService(String username,int id);

    /** 处理申诉的方法
     * @param id 订单id
     */
    String manageAppealService(String type,int id);
}
