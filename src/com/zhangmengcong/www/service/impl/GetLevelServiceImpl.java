package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.GetLevelService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function: 获取用户等级
 * @date: 2020/4/29 20:52
 */
public class GetLevelServiceImpl implements GetLevelService {
        @Override
        public  int getLevelService(String username){
            Factory factory = new Factory();
            return Integer.parseInt(factory.getQueryDao().queryDao("level","user",
                    "username","\""+username+"\""));
        }
}
