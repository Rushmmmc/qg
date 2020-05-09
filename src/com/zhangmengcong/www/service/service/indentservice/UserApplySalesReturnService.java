package com.zhangmengcong.www.service.service.indentservice;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/8 22:49
 */
public interface UserApplySalesReturnService {
    /**
     *
     * @param ifSeller 是否商家处理退货 否则为用户申请退货
     * @param indentId 订单id
     * @param type 商家处理退货方式
     * @return 提示信息
     */
    String returnGoodsService(int ifSeller,int indentId,String type);

    /** 用户退货
     *
     * @param indentId 订单 id
     * @return 提示信息
     */
    String userApplySalesReturnService(int indentId);

    /** 商家处理退货
     *
     * @param indentId  订单id
     * @param type 处理方式 同意或拒绝
     * @return 提示信息
     */
    String sellerManageReturnService(int indentId,String type);
}
