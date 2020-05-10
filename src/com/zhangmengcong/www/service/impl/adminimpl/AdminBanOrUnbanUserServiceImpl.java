package com.zhangmengcong.www.service.impl.adminimpl;

import com.zhangmengcong.www.service.service.adminservice.AdminBanOrUnbanUserService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.AdminConstant.BAN;

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
        if(ifNameFormatWrong){
            return "封禁的用户名格式不正确,只能包含数字和字母";
        }
        if(ifReasonFormatWrong){
            return "封禁的原因格式不正确,不可包含空格或特殊符号";
        }
        if(ifBan != BAN && ifBan != 0 ){
            return "仅可选择封禁或解封！";
        }
        if(ifBan == BAN){
            factory.getUpdateDao().updateDao("user","status",
                    "'已被封禁 原因("+banReason+")'","username","\""+username+"\"");
            return "用户"+username+"已被封禁( •̀ ω •́ )y";
        }else {
            factory.getUpdateDao().updateDao("user","status",
                    "'正常'","username","\""+username+"\"");
            return "用户"+username+"已被解封( •̀ ω •́ )y";
        }
    }
}
