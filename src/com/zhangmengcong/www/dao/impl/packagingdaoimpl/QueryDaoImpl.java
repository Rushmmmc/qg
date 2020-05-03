package com.zhangmengcong.www.dao.impl.packagingdaoimpl;

import com.zhangmengcong.www.dao.dao.packagingdao.QueryDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zmc
 * @function: 封装查询方法
 * @date: 2020/5/3 12:43
 */
public class QueryDaoImpl implements QueryDao {

    @Override
    public String queryDao(String column,String table,String condition,String  actualCondition) {
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String result = "";
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "select "+ column +" from "+ table +" where "+condition+" = "+actualCondition+"";
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
