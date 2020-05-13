package com.zhangmengcong.www.dao.dao;

import com.zhangmengcong.www.util.JdbcUtil;

/**
 * @author:zmc
 * @function: 检测该用户是否存在商品
 * @date: 2020/5/11 11:10
 */
public interface CheckIfUserExistGoodsDao {
    /** 检测该用户是否存在商品
     *
     * @param username 用户名
     * @return 是否存在
     */
    boolean checkIfUserExistGoods(String username);
}
