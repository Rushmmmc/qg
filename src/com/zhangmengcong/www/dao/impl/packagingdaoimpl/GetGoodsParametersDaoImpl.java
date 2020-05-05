package com.zhangmengcong.www.dao.impl.packagingdaoimpl;

import com.zhangmengcong.www.dao.dao.packagingdao.GetGoodsParametersDao;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.util.Factory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zmc
 * @function: 封装获取装有goods对象的list的方法
 * @date: 2020/5/3 17:14
 */
public class GetGoodsParametersDaoImpl implements GetGoodsParametersDao {
   @Override
   public List<Goods> getGoodsParametersDao(ResultSet rs)throws SQLException {
       List<Goods> emps = new ArrayList<>();
       Goods goods;
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
       return emps;
   }
}
