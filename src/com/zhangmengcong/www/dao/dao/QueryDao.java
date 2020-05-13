package com.zhangmengcong.www.dao.dao;

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
    String queryDao(String column,String table,String condition,String  actualCondition);

    /**
     *
     * @param column 列名
     * @param table 要查的表
     * @param condition1 条件一
     * @param actualCondition1 参数一
     * @param condition2 条件二
     * @param actualCondition2 参数二
     * @return 查询结果
     */
    String queryDao2(String column,String table,String condition1,String  actualCondition1,
                    String condition2,String  actualCondition2);
}
