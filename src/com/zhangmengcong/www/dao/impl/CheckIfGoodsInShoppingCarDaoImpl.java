package com.zhangmengcong.www.dao.impl;

import com.zhangmengcong.www.dao.dao.CheckIfGoodsInShoppingCarDao;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * @author:zmc
 * @function: 检测该商品是否已在用户的购物车里
 * @date: 2020/5/10 15:19
 */
public class CheckIfGoodsInShoppingCarDaoImpl implements CheckIfGoodsInShoppingCarDao {
    @Override
    public boolean checkIfGoodsInShoppingCar(String username, String goodsName){
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        int ifExist = 0;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            sql = "select * from indent where buyer = ? and goodsName = ? and status = ? ";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1,username);
            ptst.setString(2,goodsName);
            ptst.setString(3,"购物车");
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
