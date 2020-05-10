package com.zhangmengcong.www.dao.impl.printdaoimpl;

import com.zhangmengcong.www.dao.dao.printdao.PrintByPageDao;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.util.Factory;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.zhangmengcong.www.constant.GoodsConstant.INVERTED_SEQUENCE;
import static com.zhangmengcong.www.constant.GoodsConstant.POSITIVE_SEQUENCE;


/**
 * @author:zmc
 * @function: 打印各种表格到页面上 进行分页操作
 * @date: 2020/4/29 13:48
 */
public class PrintByPageDaoImpl implements PrintByPageDao {
    @Override
    public int findTotalCount(Goods goods){
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        int totalCount = 0;
        int amount = 0;
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "select count(*) from goods where 1 = 1 and status = \"已审核 \" ";
            StringBuilder sb = finallyAppendSql(goods,sql);
            List<Object> parametersList = finallyGetList(goods);
            ptst = conn.prepareStatement(sb.toString());
            if(goods != null) {
                for (Object o : parametersList) {
                    if (((String) o).length() < 1) {
                        continue;
                    }
                    ptst.setString(amount + 1, "%" + o + "%");
                    amount++;
                }
            }
            rs = ptst.executeQuery();
            if(rs.next()){
                totalCount=rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ptst, conn);
        }
        return totalCount;
        //计算总用户数,用来实现分页查询
    }


    /**
     * 封装冗余代码
     * @param goods 传入的查询信息
     * @return 更新后的list
     */
    @Override
    public List<Object> finallyGetList(Goods goods){
        String goodsName = null;
        String type = null;
        String goodsSeller = null;
        if(goods == null){
            return null;
        }
        if(goods.getType() != null || goods.getGoodsName() != null || goods.getSeller() != null) {
            goodsName = goods.getGoodsName();
            type = goods.getType();
            goodsSeller = goods.getSeller();
        }
        List<Object> list = new ArrayList<>();

        if(goodsName != null) {
            list.add(goodsName);
        }
        if(goodsSeller != null) {
            list.add(goodsSeller);
        }
        if(type != null) {
            list.add(type);
        }
        return list;
    }


    /**  拼接sql
     *
     * @param sb 拼接的sql
     * @param type   类型对应的值 如goosType对应的 "家用型"
     * @param typeName 类型名 如需要从查询的商品类型:"goodsType"
     * @return 拼接后的sql
     */
    @Override
    public StringBuilder getAppend(StringBuilder sb,String type, String typeName){
        if(!"".equals(type) && type != null){
            sb.append(" and ").append(typeName).append(" like ").append("?");
        }
        return sb;
    }

    /**
     * 封装冗余代码
     * @param goods 传入的查询信息
     * @param sql sql语句
     * @return 拼接后的sql语句
     */
    @Override
    public StringBuilder finallyAppendSql(Goods goods, String sql){
        StringBuilder sb = new StringBuilder(sql);
        if(goods != null) {
            sb = getAppend(sb, goods.getGoodsName(), "goodsName");
            sb = getAppend(sb, goods.getType(), "type");
            sb = getAppend(sb, goods.getSeller(), "seller");
        }
        return sb;
    }

    /**
     *
     * @param currentPage 当前页码
     * @param rows 每页条数
     * @param rangeMin 用户输入的价格最小值
     * @param rangeMax 用户输入的价格最大值
     * @param rank 用户选择价格的正序或逆序
     * @param goods 商品对象
     * @return 最终展示的表格
     */
    @Override
    public List<Goods> findByPage(int currentPage, int rows,int rangeMin,int rangeMax,String rank,Goods goods){
        Connection conn = null;
        PreparedStatement ptst = null;
        ResultSet rs = null;
        List<Goods> list = new ArrayList<>();
        List<Object> parametersList = finallyGetList(goods);
        int amount = 0;
        try {
            conn = JdbcUtil.getConnetction();
            String sql = "select * from goods where 1 = 1 and status = \"已审核 \" ";
            StringBuilder sb = finallyAppendSql(goods,sql);
            if(rangeMin != 0 && rangeMax != 0){
                sb.append(" and  price >= ").append(rangeMin).append(" and price <= ").append(rangeMax);
            }
            //用户选择正序排序价格
            if(POSITIVE_SEQUENCE.equals(rank)){
                sb.append(" ORDER BY price ASC ,sellerReputation ASC ");
            }
            //用户选择逆序排序价格
            else if(INVERTED_SEQUENCE.equals(rank)){
                sb.append(" ORDER BY price desc ,sellerReputation ASC ");
            }
            //用户不选择排序价格
            else {
                sb.append(" ORDER BY sellerReputation desc");
            }

            sb.append( "  limit ?,? ");
            ptst = conn.prepareStatement(sb.toString());
          if(goods != null) {
              for (Object o : parametersList) {
                  if (((String) o).length() == 0) {
                      continue;
                  }
                  ptst.setString(++amount, "%" + o + "%");
              }
          }
            ptst.setInt(++amount, currentPage);
            ptst.setInt(++amount, rows);
            rs = ptst.executeQuery();
            Factory factory = new Factory();
            list = factory.getGoodsParametersDao().getGoodsParametersDao(rs);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.close(rs, ptst, conn);
        }
        return list;
    }
}
