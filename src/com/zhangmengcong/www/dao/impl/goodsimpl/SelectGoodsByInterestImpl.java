package com.zhangmengcong.www.dao.impl.goodsimpl;

import com.zhangmengcong.www.dao.dao.goodsdao.SelectGoodsByInterest;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.util.Factory;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zmc
 * @function: 实现简陋的智能推送广告
 * @date: 2020/5/3 16:27
 */
public class SelectGoodsByInterestImpl implements SelectGoodsByInterest {
    @Override
    public List<Goods> selectGoodsByInterest(String username) {
        List<Goods> emps =new ArrayList<>();
        Factory factory = new Factory();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        int price = 0;
        String type = "";
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            //根据用户上一次的购买记录进行推荐
            String sql = "select price,goodsType from indent where buyer = ? order by id desc";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,username);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                price = rs.getInt(1);
                type = rs.getString(2);
            }
            //如果用户没有购买记录
            if(price == 0 || type == null){
                String sql2 = "SELECT * FROM goods LIMIT 1,3 ";
                pstmt = conn.prepareStatement(sql2);
                rs = pstmt.executeQuery();
                emps = factory.getGoodsParametersDao().getGoodsParametersDao(rs);
            }else {
                String sql3 = "SELECT * FROM goods WHERE price >= ? AND price <= ? AND TYPE = ? LIMIT 1,3 ";
                pstmt = conn.prepareStatement(sql3);
                pstmt.setInt(1,price);
                pstmt.setInt(2,4*price);
                pstmt.setString(3,type);
                rs = pstmt.executeQuery();
                emps = factory.getGoodsParametersDao().getGoodsParametersDao(rs);
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
