package com.zhangmengcong.www.service.service.indentservice;

import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:22
 */
public interface SellerChangeIndentService {
    /**
     *
     * @param indent 订单对象
     * @return 提示信息
     */
    String sellerChangeIndent(Indent indent);
}
