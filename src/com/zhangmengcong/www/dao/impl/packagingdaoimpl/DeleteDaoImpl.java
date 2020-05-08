package com.zhangmengcong.www.dao.impl.packagingdaoimpl;

import com.zhangmengcong.www.dao.dao.packagingdao.DeleteDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author:zmc
 * @function: 封装删除的dao
 * @date: 2020/5/8 12:40
 */
public class DeleteDaoImpl implements DeleteDao {
    @Override
    public boolean deleteDao(String table, String condition, String realCondition) {
        Connection conn = null;
        PreparedStatement ptst = null;
        int ifSuccess = 0;
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "delete from " + table + " where " + condition +" = " + realCondition ;
            ptst = conn.prepareStatement(sql);
            ifSuccess = ptst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ptst, conn);
        }
        return ifSuccess == 1;
    }
}
