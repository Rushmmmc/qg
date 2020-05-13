package com.zhangmengcong.www.dao.impl;

import com.zhangmengcong.www.dao.dao.QueryDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author:zmc
 * @function: 封装查询方法
 * @date: 2020/5/3 12:43
 */
public class QueryDaoImpl implements QueryDao {

    @Override
    public String queryDao(String column,String table,String condition,String  actualCondition) {
    return    queryDao2(column,table,condition,actualCondition,null,null);

    }

    @Override
    public String queryDao2(String column, String table, String condition1, String actualCondition1, String condition2, String actualCondition2) {
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String result = "";
        String sql;
        try {
            conn = JdbcUtil.getConnection();
            if(condition2 != null) {
                sql = "select " + column + " from " + table + " where " + condition1 + " = " + actualCondition1 + " and " +
                        condition2 + " = " + actualCondition2;
            }else {
                sql = "select " + column + " from " + table + " where " + condition1 + " = " + actualCondition1;
            }
            ptst = conn.prepareStatement(sql);
            rs = ptst.executeQuery();
            if(rs.next()){
                result = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ptst, conn);
        }
        return result;
    }
}
