package com.zhangmengcong.www.dao.dao.appealdao;

import com.zhangmengcong.www.po.Appeal;

/**
 * @author:zmc
 * @function: 数据库生成申诉
 * @date: 2020/5/3 23:02
 */
public interface GenerateAppealDao {
    /** 生成申诉
     *
     * @param appeal 传入的申诉对象
     */
    void generateAppealDao(Appeal appeal);
}
