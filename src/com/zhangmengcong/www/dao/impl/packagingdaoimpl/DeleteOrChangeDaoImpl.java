package com.zhangmengcong.www.dao.impl.packagingdaoimpl;

import com.zhangmengcong.www.dao.dao.packagingdao.DeleteOrChangeDao;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_DELETE;

/**
 * @author:zmc
 * @function: 根据id 进行封装删除或修改方法
 * @date: 2020/5/2 10:58
 */
public class DeleteOrChangeDaoImpl implements DeleteOrChangeDao {
    @Override
    public void deleteOrChange(String table, int ifDelete, int id, String content, String column,
                               boolean ifUseComplexChange,Indent indent) {
        String sql;
        Connection conn = null;
        PreparedStatement ptst = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnection();
            if (ifDelete == IF_DELETE){
                sql = "delete from " + table + " where id = ?";
            }else {
                sql = "update " + table + " set " +  column  +" = ? where id = ?";
            }
            //如果使用复杂修改订单
            if(ifUseComplexChange){
                sql = "update indent set GoodsName = ?,price = ?,amount = ?,totalPrice = ? where id = ?";
                ptst = conn.prepareStatement(sql);
                float totalPrice = indent.getPrice() * indent.getAmount();
                ptst.setString(1,indent.getGoodsName());
                ptst.setFloat(2,indent.getPrice());
                ptst.setInt(3,indent.getAmount());
                ptst.setFloat(4,totalPrice);
                ptst.setInt(5,indent.getId());
            } else {
                ptst = conn.prepareStatement(sql);
                ptst.setString(1, table);
                if (ifDelete == IF_DELETE) {
                    ptst.setInt(1, id);
                }
                //不需要传入对象的修改方法
                else {
                    ptst.setString(1, content);
                    ptst.setInt(2, id);
                }
            }
            ptst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(null, ptst, conn);
        }
    }
}
