package com.zhangmengcong.www.dao.impl.admindaoimpl;

import com.zhangmengcong.www.dao.dao.admindao.AdminDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static com.zhangmengcong.www.constant.AdminConstant.BAN;

/**
 * @author:zmc
 * @function: 关于管理员的功能
 * @date: 2020/4/23 19:43
 */
public class AdminDaoImpl implements AdminDao {
    /**
     * 一键成为管理员
     * @param level 等级
     * @param username 用户名
     * @return boolean
     */
    @Override
    public boolean becomeAdmin(int level, String username) {
        int count = 0;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "update user set level = ? where username =?";
            ptst = conn.prepareStatement(sql);
            ptst.setInt(1, level);
            ptst.setString(2, username);
            count = ptst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ptst, conn);
        }
        return count == 1;
    }


    @Override
    public boolean banOrUnbanUser(int ifBan, String username,String banReason){
        int count = 0;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "update user set status = ? where username = ?";
            ptst = conn.prepareStatement(sql);
            if(ifBan == BAN) {
                ptst.setString(1,"已被封禁 原因("+banReason+")");
            }else {
                ptst.setString(1, "正常");
            }
            ptst.setString(2, username);
            count = ptst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(ptst, conn);
        }
        return count == 1;
    }
}
