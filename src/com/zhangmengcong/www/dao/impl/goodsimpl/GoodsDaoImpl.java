package com.zhangmengcong.www.dao.impl.goodsimpl;

import com.zhangmengcong.www.dao.dao.goodsdao.GoodsDao;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_DELETE;

/**
 * @author:zmc
 * @function: 关于商品的数据处理
 * @date: 2020/4/30 10:11
 */
public class GoodsDaoImpl implements GoodsDao {
    @Override
    public boolean addGoods(Goods goods) {
        int count = 0;
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            sql = "insert into goods (goodsName,type,seller,sellerReputation,imformation,price,amount) " +
                    "values (?,?,?,?,?,?,?)";
            ptst = conn.prepareStatement(sql);
            //预编译 防注入
            ptst.setString(1, goods.getGoodsName());
            ptst.setString(2, goods.getType());
            ptst.setString(3, goods.getSeller());
            ptst.setInt(4,goods.getSellerReputation());
            ptst.setString(5,goods.getImformation());
            ptst.setInt(6,goods.getPrice());
            ptst.setInt(7,goods.getAmount());
            count = ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
        return count == 1;
    }

    @Override
    public void deleteOrPassGoods(int ifDelete, int goodsId){
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            if (ifDelete == IF_DELETE){
                sql = "delete from goods where id = ?";
        }else {
                sql = "update goods set status = ? where id = ?";
            }
            ptst = conn.prepareStatement(sql);
            if(ifDelete == IF_DELETE){
                ptst.setInt(1,goodsId);
            }else {
                ptst.setString(1,"已审核");
                ptst.setInt(2,goodsId);
            }
            ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
    }

    @Override
    public void sellGoodsToBuyer(int id) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            sql = "update indent set status = ? where id = ?";
            ptst = conn.prepareStatement(sql);
            ptst.setString(1,"商品在路上");
            ptst.setInt(2,id);
            ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
    }

    @Override
    public void buyGoods(Indent indent) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            sql = "insert into indent (goodsName,buyer,seller,price,amount,totalprice)" +
                    "values (?,?,?,?,?,?)";
            ptst = conn.prepareStatement(sql);
            //预编译 防注入
            ptst.setString(1, indent.getGoodsName());
            ptst.setString(2, indent.getBuyer());
            ptst.setString(3, indent.getSeller());
            ptst.setInt(4,indent.getPrice());
            ptst.setInt(5,indent.getAmount());
            ptst.setInt(6,indent.getTotalPrice());
            ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
    }
}
