package com.zhangmengcong.www.dao.dao.printdao;

import com.zhangmengcong.www.po.Goods;

import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/4/30 18:06
 */
public interface GoodsPrintDao {
    /**给管理员打印全部商品信息 便于删除 审核商品
     *
     * @return 商品List
     */
    List<Goods> selectAllGoods();
}
