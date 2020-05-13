package com.zhangmengcong.www.dao.dao;

import com.zhangmengcong.www.po.Indent;

import java.sql.PreparedStatement;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/2 10:58
 */
public interface DeleteOrChangeDao {
    void deleteOrChange(String table, int ifDelete, int id, String content, String column,
                        boolean ifUseComplexChange, Indent indent);
}
