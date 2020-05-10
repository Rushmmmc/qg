package com.zhangmengcong.www.dao.impl.appealdao;

import com.zhangmengcong.www.dao.dao.appealdao.GenerateAppealDao;
import com.zhangmengcong.www.po.Appeal;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author:zmc
 * @function: 生成申诉
 * @date: 2020/5/3 23:02
 */
public class GenerateAppealDaoImpl implements GenerateAppealDao {

    @Override
    public void generateAppealDao(Appeal appeal) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            sql = "insert into appeal (indentId,type,seller,username,reason) " +
                    "values (?,?,?,?,?)";
            ptst = conn.prepareStatement(sql);
            //预编译 防注入
            ptst.setInt(1, appeal.getIdentId());
            ptst.setString(2, appeal.getType());
            ptst.setString(3, appeal.getSeller());
            ptst.setString(4,appeal.getUsername());
            ptst.setString(5,appeal.getReason());
            ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
    }
}
