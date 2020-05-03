package com.zhangmengcong.www.dao.dao.packagingdao;

/**
 * @author:zmc
 * @function: 封装了更新一个或两个值的update数据库操作
 * @date: 2020/5/3 11:33
 */
public interface UpdateDao {
    /** 封装一个或两个值的update方法
     *
     *
     * @param table 表名
     * @param column1 列一
     * @param content1 内容一
     * @param column2 列二
     * @param content2 内容二
     * @param condition 条件名
     * @param actualCondition 实际条件
     */
    void updateDao(String table, String column1, String content1, String column2, String content2, String condition,String actualCondition);
}
