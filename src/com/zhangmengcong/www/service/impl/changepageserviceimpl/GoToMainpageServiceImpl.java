package com.zhangmengcong.www.service.impl.changepageserviceimpl;

import com.zhangmengcong.www.service.service.pageservice.GoToMainpageService;

import static com.zhangmengcong.www.constant.UserConstant.ADMIN_LEVEL;
import static com.zhangmengcong.www.constant.UserConstant.VISITOR_LEVEL;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 10:49
 */
public class GoToMainpageServiceImpl implements GoToMainpageService {
    @Override
    public String goToMainpageService(int level) {
        if (level == ADMIN_LEVEL) {
           return "/adminPage.jsp";
        } else if (level == VISITOR_LEVEL) {
            return "/visitor.jsp";
        } else {
           return  "/mainpage.jsp";
        }
    }
}
