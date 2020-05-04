package com.zhangmengcong.www.service.service.formatservice;

/**
 * @author:zmc
 * @function: 正则判断信息是否为空 是否包含中文 是否包含特殊符号
 * @date: 2020/5/4 9:48
 */
public interface FormatService {
    /**
     *
     * @param message 要检验的字符
     * @return 是否符合格式
     */
   boolean formatService(String message);

    /**
     *
     * @param message 邮箱
     * @return 邮箱是否包含中文 特殊符号
     */
   boolean mailFormatService(String message);
}
