package com.zhangmengcong.www.dao.impl.printdaoimpl;

import com.zhangmengcong.www.dao.dao.printdao.MessagePrintDao;
import com.zhangmengcong.www.po.Message;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zmc
 * @function: 打印商家和用户的留言
 * @date: 2020/5/6 22:14
 */
public class MessagePrintDaoImpl implements MessagePrintDao {

    @Override
    public List<Message> select(int indentId) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Message message;
        List<Message> emps = new ArrayList<>();
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            String sql = "select * from message where indentId = ? order by id desc ";
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,indentId);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            message = new Message();
            message.setId(rs.getInt("id"));
            message.setBuyerName(rs.getString("buyerName"));
            message.setBeginDate(rs.getTimestamp("beginDate"));
            message.setBuyerMessage(rs.getString("buyerMessage"));
            message.setSellerName(rs.getString("sellerName"));
            message.setSellerMessage(rs.getString("sellerMessage"));
            emps.add(message);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(rs,pstmt,conn);
        }
        return emps;
        //直接返回emps对象即可
    }
}
