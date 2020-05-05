package com.zhangmengcong.www.service.impl.adminimpl;

import com.zhangmengcong.www.service.service.adminservice.BecomeAdminService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.UserConstant.ADMIN_LEVEL;
import static com.zhangmengcong.www.constant.UserConstant.USER_LEVEL;

/**
 * @author:zmc
 * @function: 修改用户等级的实现类
 * @date: 2020/4/29 22:56
 */
public class BecomeAdminServiceImpl implements BecomeAdminService {
     @Override
     public String becomeAdminServiceImpl(int level, String username){
        Factory factory = new Factory();
        //检测是否存在中文或特殊字符
        boolean ifFormatWrong = factory.getFormatService().formatService(String.valueOf(level));
        //格式正确
        if(!ifFormatWrong) {
            //等级只能为2或3
            if(level == USER_LEVEL || level == ADMIN_LEVEL) {
                //修改成功
                if(factory.getAdminDao().becomeAdmin(level, username)){
                    return "";
                }
            }
        }
    return "该等级不存在";
     }
}
