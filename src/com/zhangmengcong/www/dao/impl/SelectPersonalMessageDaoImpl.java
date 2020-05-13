package com.zhangmengcong.www.dao.impl;

import com.zhangmengcong.www.dao.dao.SelectPersonalMessageDao;
import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:11
 */
public class SelectPersonalMessageDaoImpl implements SelectPersonalMessageDao {
    @Override
    public List<User> selectPersonalMessage(String username){
        List<User> emps =new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        User user = new User();
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            String sql = "select * from user where username = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            rs = pstmt.executeQuery();
            if(rs.next()){
                user.setUsername(rs.getString("username"));
                user.setId(rs.getInt("id"));
                user.setMailAddress(rs.getString("mailAddress"));
                user.setExp(rs.getInt("exp"));
                user.setIntegral(rs.getInt("integral"));
                user.setPassword(rs.getString("password"));
                user.setRegisterDate(rs.getTimestamp("register_date"));
                user.setReputationPoint(rs.getInt("reputationPoint"));
                user.setStatus(rs.getString("status"));
                emps.add(user);
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
