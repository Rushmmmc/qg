package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function: 扣除被投诉商家的信誉分
 * @date: 2020/5/3 21:06
 */
public interface ManageAppealService {
    /** 扣除被投诉商家的信誉分
     *
     * @param username 可怜的商家
     */
    void minusSellerReputationService(String username);

    /** 处理申诉的方法
     * @param username 管理员用户名
     * @param type 处理类型
     * @param id 订单id
     * @param appealId 申诉号id
     * @return 提示信息
     */
    String manageAppealService(String type,int id,int appealId,String username);
}
