package com.zhangmengcong.www.dao.impl;

import com.zhangmengcong.www.dao.dao.RegisterDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:19
 */
public class RegisterDaoImpl implements RegisterDao {
    /**
     * @param username    用户名
     * @param password    密码
     * @param mailAddress 电话
     * @return 用于注册
     */
    @Override
    public boolean register(String username, String password, String mailAddress) {
        int count = 0;
        String sql2;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            sql2 = "insert into user (username,password,mailaddress) values (?,?,?)";
            ptst = conn.prepareStatement(sql2);
            //预编译 防注入
            ptst.setString(1, username);
            ptst.setString(2, password);
            ptst.setString(3, mailAddress);
            count = ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);

        }
        return count == 1;
    }
    //用于注册
}
