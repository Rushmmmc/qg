package com.zhangmengcong.www.constant;

import java.util.regex.Pattern;

/**
 * @author:zmc
 * @function: 关于数据格式验证的常量
 * @date: 2020/5/4 9:20
 */
public class FormatConstant {
    public static Pattern IF_INCLUDE_CHINESE_PATTERN = Pattern.compile(".*[\\u4e00-\\u9fa5]{1,}.*");
    public static Pattern IF_INCLUDE_SYMBOL = Pattern.compile("[\\u4E00-\\u9FA5+a-zA-Z0-9_-]{1,50}$");
    public static Pattern IF_MAIL_FORMAT_CORRECT = Pattern.compile("^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$");
}
