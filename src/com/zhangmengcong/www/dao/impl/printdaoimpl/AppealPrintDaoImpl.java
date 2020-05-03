package com.zhangmengcong.www.dao.impl.printdaoimpl;

import com.zhangmengcong.www.dao.dao.printdao.AppealPrintDao;
import com.zhangmengcong.www.po.Appeal;
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
 * @date: 2020/5/3 19:02
 */
public class AppealPrintDaoImpl implements AppealPrintDao {
    @Override
    public List<Appeal> appealPrintDao() {
        Factory factory = new Factory();
        List<Appeal> list =new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Appeal appeal;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            String sql = "select * from appeal";
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while(rs.next()) {
            appeal = new Appeal();
            appeal.setAppealDate(rs.getTimestamp("appealDate"));
            appeal.setId(rs.getInt("id"));
            appeal.setReason(rs.getString("reason"));
            appeal.setType(rs.getString("type"));
            appeal.setStatus(rs.getString("status"));
            appeal.setUsername(rs.getString("username"));
            list.add(appeal);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            JdbcUtil.close(rs,pstmt,conn);
        }
        return list;
        //直接返回对象即可
    }
}
