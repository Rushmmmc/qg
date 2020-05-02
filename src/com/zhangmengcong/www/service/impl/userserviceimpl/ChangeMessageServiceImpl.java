package com.zhangmengcong.www.service.impl.userserviceimpl;

import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.service.service.userservice.ChangeMessageService;
import com.zhangmengcong.www.util.Factory;


/**
 * @author:zmc
 * @function: 修改用户信息的服务
 * @date: 2020/4/29 20:23
 */
public class ChangeMessageServiceImpl implements ChangeMessageService {
       @Override
       public boolean getChangeMessageServiceImpl(User user,String username) {
           Factory factory = new Factory();
           return factory.getUserDao().change(user,username);
        }
}
