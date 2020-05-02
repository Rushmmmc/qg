package com.zhangmengcong.www.dao.impl.printdaoimpl;

import com.zhangmengcong.www.dao.dao.printdao.GoodsPrintDao;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/4/30 18:01
 */
public class GoodsPrintDaoImpl implements GoodsPrintDao {
    @Override
    public List<Goods> selectAllGoods(){
        List<Goods> emps =new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Goods goods;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            String sql = "select * from goods";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()){
                goods = new Goods();
                goods.setAmount(rs.getInt("amount"));
                goods.setPrice(rs.getInt("price"));
                goods.setImformation(rs.getString("imformation"));
                goods.setGoodsName(rs.getString("goodsName"));
                goods.setSellerReputation(rs.getInt("sellerReputation"));
                goods.setType(rs.getString("type"));
                goods.setSeller(rs.getString("seller"));
                goods.setId(rs.getInt("id"));
                goods.setStatus(rs.getString("status"));
                emps.add(goods);
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
