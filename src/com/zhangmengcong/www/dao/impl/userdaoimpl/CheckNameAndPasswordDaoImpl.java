package com.zhangmengcong.www.dao.impl.userdaoimpl;

import com.zhangmengcong.www.dao.dao.userdao.CheckNameAndPasswordDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:21
 */
public class CheckNameAndPasswordDaoImpl implements CheckNameAndPasswordDao {
    /**
     * @param username 用户名
     * @param password 密码
     * @return 验证用户账号密码的方法
     */
    @Override
    public boolean check(String username, String password) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection conn = null;
        boolean ifMessageCorrect = false;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            String sql = "SELECT * FROM USER WHERE username = ? AND PASSWORD = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            ifMessageCorrect = rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ps, conn);
        }
        return ifMessageCorrect;
    }
    //查询用户是否存在方法
}
