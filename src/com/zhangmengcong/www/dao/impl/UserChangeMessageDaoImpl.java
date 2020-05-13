package com.zhangmengcong.www.dao.impl;

import com.zhangmengcong.www.dao.dao.UserChangeMessageDao;
import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 14:27
 */
public class UserChangeMessageDaoImpl implements UserChangeMessageDao {
    /** 修改用户信息
     * @param  username 用户的原用户名
     * @param user  包含用户各种信息的对象
     * @return /给用户改密码
     */
    @Override
    public boolean change(User user, String username) {
        Connection conn = null;
        PreparedStatement ptst = null;
        int ifSuccess = 0;
        try {
            //数据库的常规操作
            conn = JdbcUtil.getConnection();
            String sql = "UPDATE USER SET PASSWORD =?,username =?,mailAddress =? WHERE username = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, user.getPassword());
            ptst.setString(2, user.getUsername());
            ptst.setString(3, user.getMailAddress());
            ptst.setString(4,username);
            ifSuccess = ptst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ptst, conn);
        }
        return ifSuccess == 1;
    }
}
