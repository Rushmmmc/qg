package com.zhangmengcong.www.dao.impl.UserDaoImpl;

import com.zhangmengcong.www.dao.dao.UserDao.UserDao;
import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author:zmc
 * @function: 处理用户信息 登录 注册等
 * @date: 2020/4/23 20:36
 */
public class UserDaoImpl implements UserDao {
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
            conn = JdbcUtil.getConnetction();
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
            conn = JdbcUtil.getConnetction();
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


    /**
     * @param username 用户名
     * @return /获取用户的等级
     */
    @Override
    public int getLevel(String username) {
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        int level = 1;
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "select level from user where username = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, username);
            rs = ptst.executeQuery();
            if (rs.next()) {
                level = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ptst, conn);
        }
        return level;
    }
//获取用户的等级


    /** 修改用户信息
     * @param  username 用户的原用户名
     * @param user  包含用户各种信息的对象
     * @return /给用户改密码
     */
    @Override
    public boolean change(User user,String username) {
        Connection conn = null;
        PreparedStatement ptst = null;
        int ifSuccess = 0;
        try {
            //数据库的常规操作
            conn = JdbcUtil.getConnetction();
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




    @Override
    public boolean checkMail(String mailAddress) {
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        int count = 0;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            String sql = "select * from user where mailAddress = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, mailAddress);
            rs = ptst.executeQuery();
            if (rs.next()) {
                count = 1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ptst, conn);

        }
        return count == 1;
    }


    /**
     *
     * @param address 邮箱地址
     * @return 根据邮箱地址获取用户名
     */
    @Override
    public String getusername(String address) {
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        String username = "";
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "select username from user where mailAddress = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1, address);
            rs = ptst.executeQuery();
            if (rs.next()) {
                username = rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ptst, conn);
        }
        return username;
    }
    //根据邮箱地址获取用户名

    @Override
    public boolean selectUserIfBan(String username){
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs;
        String status = "";
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            sql = "select status from user where username = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1,username);
            rs = ptst.executeQuery();
            if(rs.next()){
                status = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
        return status.contains("封禁");
    }



}