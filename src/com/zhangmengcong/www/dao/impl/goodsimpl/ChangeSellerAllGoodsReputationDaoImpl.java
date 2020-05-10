package com.zhangmengcong.www.dao.impl.goodsimpl;

import com.zhangmengcong.www.dao.dao.goodsdao.ChangeSellerAllGoodsReputationDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 15:11
 */
public class ChangeSellerAllGoodsReputationDaoImpl implements ChangeSellerAllGoodsReputationDao {
    @Override
    public boolean changeSellerAllGoodsReputation(String sellerName) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        int ifSuccess = 0;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            sql = "update goods set sellerReputation = sellerReputation - 1 where seller = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1,sellerName);
            ifSuccess = ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
        return ifSuccess != 0;
    }
}
