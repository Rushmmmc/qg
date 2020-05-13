package com.zhangmengcong.www.dao.dao;

import com.zhangmengcong.www.po.Goods;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author:zmc
 * @function: 封装获取装有goods对象的list的方法
 * @date: 2020/5/3 17:14
 */
public interface GetGoodsParametersDao {
    /** 封装获取装有goods对象的list的方法
     *
     * @param rs 传入结果集
     * @return  装有goods对象的list
     * @throws SQLException sql异常
     */
    List<Goods> getGoodsParametersDao(ResultSet rs)throws SQLException;
}
