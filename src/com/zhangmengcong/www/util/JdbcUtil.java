package com.zhangmengcong.www.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Properties;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 22:52
 */
public class JdbcUtil {
    private static DataSource ds;
    static {
        Properties pro = new Properties();
        try {
            pro.load(Objects.requireNonNull(JdbcUtil.class.getClassLoader().getResourceAsStream(
                    "jdbc.properties")));
            try {
                ds = DruidDataSourceFactory.createDataSource(pro);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
    public static void close(ResultSet rs, Statement stmt, Connection conn){
     if(stmt != null){
         try {
             stmt.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }
     }
        if(conn != null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Statement stmt, Connection conn){
         close(null,stmt,conn);
    }

    public static DataSource getDateSource(){
        return ds;
    }
}
