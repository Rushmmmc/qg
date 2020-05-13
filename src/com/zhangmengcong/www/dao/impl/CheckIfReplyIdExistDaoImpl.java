package com.zhangmengcong.www.dao.impl;

import com.zhangmengcong.www.dao.dao.CheckIfReplyIdExistDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/6 23:04
 */
public class CheckIfReplyIdExistDaoImpl implements CheckIfReplyIdExistDao {


    @Override
    public boolean checkIfReplyIdExist(int messageId,int indentId) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs;
        int ifExist = 0;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            sql = "select * from message where id = ? and indentId = ?";
            ptst = conn.prepareStatement(sql);
            //预编译 防注入
            ptst.setInt(1,messageId);
            ptst.setInt(2,indentId);
            rs = ptst.executeQuery();
            if(rs.next()){
                ifExist = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
        return ifExist == 1;
    }
}