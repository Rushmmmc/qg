package com.zhangmengcong.www.service.service;

import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 14:47
 */
public interface IfMailExistService {
    /** 检查用户输入的邮箱是否存在
     *
     * @param mailAddress 邮箱地址
     * @return 是否存在
     */
    boolean ifMailExistService(String mailAddress);
}
