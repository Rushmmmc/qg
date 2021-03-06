package com.zhangmengcong.www.dao.impl;

import com.zhangmengcong.www.dao.dao.IndentPrintDao;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;

/**
 * @author:zmc
 * @function: 打印订单信息
 * @date: 2020/5/1 20:12
 */
public class IndentPrintDaoImpl implements IndentPrintDao {
    @Override
    public List<Indent> selectPersonalIndent(int id,String username,int ifSeller,boolean ifShoppingCar){
        List<Indent> emps =new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Indent indent;
        String sql;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            //如果选择打印卖家订单功能
            if(ifSeller == IF_SELLER) {
                sql = "select * from indent where seller = ? and status != \"购物车\" ";
            }
            else{
                sql = "select * from indent where buyer = ? and status != \"购物车\" ";
                if(ifShoppingCar){
                    sql = "select * from indent where buyer = ? and status = \"购物车\" ";
                }
            }
            pstmt = conn.prepareStatement(sql + " ORDER BY id desc");
            pstmt.setString(1,username);
            if(id != 0 ){
                sql = "select * from indent where id = ?";
                pstmt = conn.prepareStatement(sql + " ORDER BY id desc");
                pstmt.setInt(1,id);
            }

            rs = pstmt.executeQuery();
            while(rs.next()){
                indent = new Indent();
                indent.setGoodsName(rs.getString("goodsName"));
                indent.setBuyer(rs.getString("buyer"));
                indent.setId(rs.getInt("id"));
                indent.setPrice(rs.getFloat("price"));
                indent.setSeller(rs.getString("seller"));
                indent.setStatus(rs.getString("status"));
                indent.setTotalPrice(rs.getFloat("totalPrice"));
                indent.setAmount(rs.getInt("amount"));
                indent.setSellerMessage(rs.getString("sellerMessage"));
                indent.setBuyerMessage(rs.getString("buyerMessage"));
                indent.setReputation(rs.getString("reputation"));
                indent.setUseIntegral(rs.getInt("useIntegral"));
                indent.setActuallyPrice(rs.getFloat("actuallyPrice"));
                indent.setGoodsType(rs.getString("goodsType"));
                indent.setEvaluate(rs.getString("evaluate"));
                indent.setIfBuyerDelete(rs.getInt("ifBuyerDelete"));
                indent.setIfSellerDelete(rs.getInt("ifSellerDelete"));
                indent.setLastAmount(rs.getInt("lastAmount"));
                emps.add(indent);
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
