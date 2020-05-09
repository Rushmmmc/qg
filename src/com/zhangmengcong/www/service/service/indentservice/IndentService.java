package com.zhangmengcong.www.service.service.indentservice;

import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function: 删除或修改订单
 * @date: 2020/5/2 12:20
 */
public interface IndentService {
    /** 检查订单id
     *
     * @param indentId 订单id
     * @return 订单id格式是否有误
     */
    boolean ifIndentIdFormatWrong(int indentId);

    /** 删除订单
     *
     * @param ifSeller 是否商家
     * @param indentId 订单id
     * @return 提示信息
     */
    String deleteIndent(int ifSeller,int indentId);
    /**
     *
     * @param evaluate 评价
     * @param indentId 订单id
     * @return 提示信息
     */
    String userEvaluateIndent(String evaluate,int indentId);

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

    /**
     *
     * @param indent 订单对象
     * @return 提示信息
     */
    String sellerChangeIndent(Indent indent);

    /** 完成订单
     *
     * @param indentId 订单id
     * @param username 用户
     * @return 提示信息
     */
    String finishIndent(int indentId, String username);

    /** 商家删除订单
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String sellerSendGoods(int indentId);


    /**
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String deleteShoppingCarIndent(int indentId);

    /**
     *
     * @param indent 订单对象
     * @return 提示信息
     */
    String buyGoodsFromShoppingCar(Indent indent);

    /**
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String giveGoodsReputation(int indentId);

    /**
     *
     * @param indentId 订单id
     * @return 提示信息
     */
    String giveBadReputation(int indentId);


}



