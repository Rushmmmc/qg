package com.zhangmengcong.www.service.impl.adminimpl;

import com.zhangmengcong.www.service.service.adminservice.AdminBanOrUnbanUserService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function: 管理员封禁/解封用户 做好数据检验
 * @date: 2020/5/1 18:22
 */
public class AdminBanOrUnbanUserServiceImpl implements AdminBanOrUnbanUserService {
    @Override
    public String adminBanOrUnbanUserService(int ifBan, String username,String banReason){
        Factory factory = new Factory();
        boolean ifNameFormatWrong = factory.getFormatService().formatService(username);
        boolean ifReasonFormatWrong = factory.getFormatService().ifIncludeSymbol(banReason);
        //当格式合法
        if(!ifNameFormatWrong && !ifReasonFormatWrong) {
            if(factory.getAdminDao().banOrUnbanUser(ifBan, username, banReason)) {
                return "处理成功!";
            }
        }
            return "信息格式不正确或该用户不存在";
    }
}
