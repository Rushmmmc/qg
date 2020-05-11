package com.zhangmengcong.www.dao.impl.goodsimpl;

import com.zhangmengcong.www.dao.dao.goodsdao.CheckIfUserExistGoodsDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/11 11:10
 */
public class CheckIfUserExistGoodsDaoImpl implements CheckIfUserExistGoodsDao {
    @Override
    public boolean checkIfUserExistGoods(String username){
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        int ifExist = 0;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            sql = "select * from goods where seller = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1,username);
            rs = ptst.executeQuery();
            if(rs.next()){
                ifExist = 1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ptst, conn);
        }
        return ifExist == 1;
    }
}
