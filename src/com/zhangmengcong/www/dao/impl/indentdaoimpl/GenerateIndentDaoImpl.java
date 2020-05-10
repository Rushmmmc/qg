package com.zhangmengcong.www.dao.impl.indentdaoimpl;

import com.zhangmengcong.www.dao.dao.indentdao.GenerateIndentDao;
import com.zhangmengcong.www.po.Indent;

import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;


/**
 * @author:zmc
 * @function: 对订单的数据处理
 * @date: 2020/5/2 10:48
 */
public class GenerateIndentDaoImpl implements GenerateIndentDao {


    @Override
    public void generateIndent(Indent indent) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            sql = "insert into indent (goodsName,buyer,seller,price,amount,totalprice," +
                    "status,useIntegral,actuallyPrice,goodsType,lastAmount)" +
                    "values (?,?,?,?,?,?,?,?,?,?,?)";
            ptst = conn.prepareStatement(sql);
            //预编译 防注入
            ptst.setString(1, indent.getGoodsName());
            ptst.setString(2, indent.getBuyer());
            ptst.setString(3, indent.getSeller());
            ptst.setFloat(4,indent.getPrice());
            ptst.setInt(5,indent.getAmount());
            ptst.setFloat(6,indent.getTotalPrice());
            ptst.setString(7,indent.getStatus());
            ptst.setInt(8,indent.getUseIntegral());
            ptst.setFloat(9,indent.getActuallyPrice());
            ptst.setString(10,indent.getGoodsType());
            ptst.setInt(11,indent.getLastAmount());
            ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
    }


}
