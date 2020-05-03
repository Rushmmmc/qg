package com.zhangmengcong.www.dao.dao.packagingdao;

import java.util.List;

/**
 * @author:zmc
 * @function: 封装查询方法
 * @date: 2020/5/3 12:43
 */
public interface QueryDao {
    /** 封装查询方法
     * @param table 要查的表
     * @param column 列名
     * @param condition 条件名
     * @param actualCondition 实际条件
     * @return 查询到的装有信息
     */
    String queryDao(String table,String column,String condition,String  actualCondition);
}
