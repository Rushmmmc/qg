package com.zhangmengcong.www.service.service.pageservice;

/**
 * @author:zmc
 * @function: 给用户跳转到合适的主页面
 * @date: 2020/5/1 10:46
 */
public interface GoToMainpageService {
        /** 给用户跳转到合适的主页面
         *
         * @param level 根据用户等级
         * @return 跳转到的地址
         */
        String goToMainpageService(int level);
}
