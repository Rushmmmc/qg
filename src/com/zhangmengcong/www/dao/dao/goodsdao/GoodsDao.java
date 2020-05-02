package com.zhangmengcong.www.dao.dao.goodsdao;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.Indent;

/**
 * @author:zmc
 * @function: 商品相关的数据处理
 * @date: 2020/4/30 10:11
 */
public interface GoodsDao {
    /** 用户提交商品给管理员审核
     *
     * @param goods 包含商品各种信息的对象
     * @return 是否成功
     */
    boolean addGoods(Goods goods);

    /** 删除或通过商品
     *
     * @param ifDelete 是否使用删除功能 否则即是通过
     * @param goodsId 对应的商品id
     */
    void deleteOrPassGoods(int ifDelete,int goodsId);

    /**修改订单的状态为商品在路上
     * @param id  订单id
     */
    void sellGoodsToBuyer(int id);

    /**用户买商品 生成订单
     *
     * @param indent 封装好的订单对象
     */
    void buyGoods(Indent indent);
}
