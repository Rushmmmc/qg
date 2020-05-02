package com.zhangmengcong.www.dao.impl.printdaoimpl;

import com.zhangmengcong.www.dao.dao.printdao.IndentPrintDao;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 20:12
 */
public class IndentPrintDaoImpl implements IndentPrintDao {
    @Override
    public List<Indent> selectPersonalIndent(String username,int ifSeller){
        List<Indent> emps =new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Indent indent = new Indent();
        String sql;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            //如果选择打印卖家订单功能
            if(ifSeller == IF_SELLER) {
                sql = "select * from indent where seller = ?";
            }else{
                sql = "select * from indent where buyer = ? ";
            }
            StringBuilder sb = new StringBuilder(sql);
            sb.append(" ORDER BY id desc");
            pstmt = conn.prepareStatement(sb.toString());
            pstmt.setString(1,username);
            rs = pstmt.executeQuery();
            while(rs.next()){
                indent = new Indent();
                indent.setGoodsName(rs.getString("goodsName"));
                indent.setBuyer(rs.getString("buyer"));
                indent.setId(rs.getInt("id"));
                indent.setPrice(rs.getInt("price"));
                indent.setSeller(rs.getString("seller"));
                indent.setStatus(rs.getString("status"));
                indent.setTotalPrice(rs.getInt("totalPrice"));
                indent.setAmount(rs.getInt("amount"));
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
