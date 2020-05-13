package com.zhangmengcong.www.dao.impl;

import com.zhangmengcong.www.dao.dao.AddNewMessageDao;
import com.zhangmengcong.www.po.Message;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 15:27
 */
public class AddNewMessageDaoImpl implements AddNewMessageDao {
    @Override
    public boolean addNewMessageDao(Message message) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        int ifSuccess = 0;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            if(message.getBuyerMessage() == null){
                sql = "insert into message (indentId,buyerName,sellerName,sellerMessage)" +
                        "values (?,?,?,?)";
            }
            else {
                sql = "insert into message (indentId,buyerName,sellerName,buyerMessage)" +
                        "values (?,?,?,?)";
            }
            ptst = conn.prepareStatement(sql);
            //预编译 防注入
            ptst.setInt(1, message.getIndentId());
            ptst.setString(2, message.getBuyerName());
            ptst.setString(3, message.getSellerName());
            if(message.getBuyerMessage() == null){
                ptst.setString(4, message.getSellerMessage());
            }
            else {
                ptst.setString(4, message.getBuyerMessage());
            }
            ifSuccess = ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
        return ifSuccess == 1;
    }
}
