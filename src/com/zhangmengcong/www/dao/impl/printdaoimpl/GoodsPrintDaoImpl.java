package com.zhangmengcong.www.dao.impl.printdaoimpl;

import com.zhangmengcong.www.dao.dao.printdao.GoodsPrintDao;
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
 * @function:
 * @date: 2020/4/30 18:01
 */
public class GoodsPrintDaoImpl implements GoodsPrintDao {
    @Override
    public List<Goods> selectAllGoods(){
        Factory factory = new Factory();
        List<Goods> emps =new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            String sql = "select * from goods order by id desc ";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            emps = factory.getGoodsParametersDao().getGoodsParametersDao(rs);
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(rs,pstmt,conn);
        }
        return emps;
        //直接返回emps对象即可
    }
}
