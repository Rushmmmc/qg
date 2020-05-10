package com.zhangmengcong.www.service.impl.userserviceimpl;

import com.zhangmengcong.www.service.service.userservice.CheckIfUserBeBanedService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 19:28
 */
public class CheckIfUserBeBanedServiceImpl implements CheckIfUserBeBanedService {
    @Override
    public boolean checkIfUserBeBanedService(String username){
        Factory factory = new Factory();
      return   factory.getQueryDao().queryDao("status","user",
              "username","\""+username+"\"").contains("封禁");
    }
}
