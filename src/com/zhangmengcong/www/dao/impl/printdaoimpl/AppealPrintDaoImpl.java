package com.zhangmengcong.www.dao.impl.printdaoimpl;

import com.zhangmengcong.www.dao.dao.printdao.AppealPrintDao;
import com.zhangmengcong.www.po.Appeal;
import com.zhangmengcong.www.util.JdbcUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:zmc
 * @function: 打印申诉信息
 * @date: 2020/5/3 19:02
 */
public class AppealPrintDaoImpl implements AppealPrintDao {
    @Override
    public List<Appeal> appealPrintDao(boolean ifAll,String username) {
        List<Appeal> list =new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Appeal appeal;
        try {
            //数据库的常规操作~~
            conn = JdbcUtil.getConnetction();
            String sql = "select * from appeal where username = ? order by id desc";
            if(ifAll){
                sql = "select * from appeal order by id desc";
            }
            pstmt = conn.prepareStatement(sql);
            if(!ifAll){
                pstmt.setString(1,username);
            }
            rs = pstmt.executeQuery();
            while(rs.next()) {
            appeal = new Appeal();
            appeal.setAppealDate(rs.getTimestamp("appealDate"));
            appeal.setId(rs.getInt("id"));
            appeal.setReason(rs.getString("reason"));
            appeal.setType(rs.getString("type"));
            appeal.setStatus(rs.getString("status"));
            appeal.setUsername(rs.getString("username"));
            appeal.setIdentId(rs.getInt("indentId"));
            appeal.setSeller(rs.getString("seller"));
            appeal.setMessage(rs.getString("message"));
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
