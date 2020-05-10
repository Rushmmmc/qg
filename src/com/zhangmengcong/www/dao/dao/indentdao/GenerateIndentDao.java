package com.zhangmengcong.www.dao.dao.indentdao;

import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function: 订单的数据处理
 * @date: 2020/5/2 10:48
 */
public interface GenerateIndentDao {
    /**用户买商品 生成订单
     *
     * @param indent 封装好的订单对象
     */
    void generateIndent(Indent indent);




}
