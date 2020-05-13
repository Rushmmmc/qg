package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.CheckIfUserExistGoodsService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/11 11:06
 */
public class CheckIfUserExistGoodsServiceImpl implements CheckIfUserExistGoodsService {
    @Override
    public boolean checkIfUserExistGoodsService(String username){
        Factory factory = new Factory();
        return factory.getCheckIfUserExistGoodsDao().checkIfUserExistGoods(username);
    }
}
