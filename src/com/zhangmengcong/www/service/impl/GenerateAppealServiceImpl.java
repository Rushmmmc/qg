package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.po.Appeal;
import com.zhangmengcong.www.service.service.GenerateAppealService;
import com.zhangmengcong.www.util.Factory;


import static com.zhangmengcong.www.constant.UserConstant.MESSAGE_LENGTH;
import static com.zhangmengcong.www.constant.UserConstant.USER_LEVEL;

/**
 * @author:zmc
 * @function: 用户投诉 生成申诉
 * @date: 2020/5/3 22:59
 */
public class GenerateAppealServiceImpl implements GenerateAppealService {
    @Override
    public String generateAppealService(Appeal appeal) {
        Factory factory = new Factory();
        //判断用户等级 不为普通用户等级的话拦截
        int level = Integer.parseInt(factory.getQueryDao().queryDao("level","user","username",
                "\""+appeal.getUsername()+"\""));
        if(level != USER_LEVEL){
            return "只有普通用户可以提交申诉";
        }
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(appeal.getId()));
        boolean ifReasonFormatWrong = factory.getFormatService().ifIncludeSymbol(appeal.getReason());
        if(ifIdFormatWrong){
            return "申诉id格式有误┭┮﹏┭┮";
        }
        if(ifReasonFormatWrong){
            return "申诉原因格式有误┭┮﹏┭┮";
        }
        if(appeal.getReason().length() > MESSAGE_LENGTH){
            return "申诉原因请不要超过15个汉字┭┮﹏┭┮";
        }else {
            appeal.setSeller(factory.getQueryDao().queryDao("seller","indent","id",String.valueOf(appeal.getIdentId())));
            factory.getGenerateAppealDao().generateAppealDao(appeal);
            return "提交申诉成功( •̀ ω •́ )y";
        }

    }
}
