package com.zhangmengcong.www.service.impl.userserviceimpl;

import com.zhangmengcong.www.po.Appeal;
import com.zhangmengcong.www.service.service.userservice.GenerateAppealService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.UserConstant.COMMIT_APPEAl_SUCCESS;

/**
 * @author:zmc
 * @function: 用户投诉 生成申诉
 * @date: 2020/5/3 22:59
 */
public class GenerateAppealServiceImpl implements GenerateAppealService {
    @Override
    public String generateAppealService(Appeal appeal) {
        Factory factory = new Factory();
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(appeal.getId()));
        boolean ifReasonFormatWrong = factory.getFormatService().ifIncludeSymbol(appeal.getReason());

        if(!ifIdFormatWrong || !ifReasonFormatWrong){
            appeal.setSeller(factory.getQueryDao().queryDao("seller","indent","id",String.valueOf(appeal.getIdentId())));
            factory.getGenerateAppealDao().generateAppealDao(appeal);
            return COMMIT_APPEAl_SUCCESS;
        }else {
            return "信息格式不正确┭┮﹏┭┮";
        }

    }
}
