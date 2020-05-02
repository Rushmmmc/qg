package com.zhangmengcong.www.service.impl.adminimpl;

import com.zhangmengcong.www.service.service.adminservice.AdminBanOrUnbanUserService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 18:22
 */
public class AdminBanOrUnbanUserServiceImpl implements AdminBanOrUnbanUserService {
    @Override
    public boolean adminBanOrUnbanUserService(int ifBan, String username,String banReason){
        Factory factory = new Factory();
        return factory.getAdminDao().banOrUnbanUser(ifBan,username,banReason);
    }
}
