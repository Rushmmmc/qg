package com.zhangmengcong.www.dao.impl.packagingdaoimpl;

import com.zhangmengcong.www.dao.dao.packagingdao.UpdateDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author:zmc
 * @function: update 对于一个或两个int参数的封装方法
 * @date: 2020/5/3 11:36
 */
public class UpdateDaoImpl implements UpdateDao {
    @Override
    public void updateDao(String table, String column1, String content1, String column2, String content2, String condition,String actualCondition){
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "update " + table + " set " + column1 +" = " + content1 + " where " + condition + " = " + actualCondition;
             if(column2 != null && content2 != null) {
                 sql = "update " + table + " set " + column1 + " = " + content1 + "," + column2 + " = " + content2 + " where " + condition + " = " + actualCondition;
             }
             ptst = conn.prepareStatement(sql);
             ptst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ptst, conn);
        }
    }
}
