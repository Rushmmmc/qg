package com.zhangmengcong.www.service.impl.adminimpl;

import com.zhangmengcong.www.service.service.adminservice.BecomeAdminService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function: 修改用户等级的实现类
 * @date: 2020/4/29 22:56
 */
public class BecomeAdminServiceImpl implements BecomeAdminService {
     @Override
     public boolean becomeAdminServiceImpl(int level, String username){
        Factory factory = new Factory();
         return factory.getAdminDao().becomeAdmin(level,username);
    }
}
