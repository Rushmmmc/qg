package com.zhangmengcong.www.dao.impl.messageimpl;

import com.zhangmengcong.www.dao.dao.message.AddMessageDao;
import com.zhangmengcong.www.po.Message;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/6 23:04
 */
public class AddMessageDaoImpl implements AddMessageDao {
    @Override
    public boolean addNewMessageDao(Message message) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        int ifSuccess = 0;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            sql = "insert into message (indentId,buyerName,sellerName,buyerMessage)" +
                    "values (?,?,?,?)";
            ptst = conn.prepareStatement(sql);
            //预编译 防注入
            ptst.setInt(1, message.getIndentId());
            ptst.setString(2, message.getBuyerName());
            ptst.setString(3, message.getSellerName());
            ptst.setString(4, message.getBuyerMessage());
            ifSuccess = ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
        return ifSuccess == 1;
    }
}