package com.zhangmengcong.www.constant;

import java.util.regex.Pattern;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/4 9:20
 */
public class FormatConstant {
    public static Pattern IF_INCLUDE_CHINESE_PATTERN = Pattern.compile("[\u4e00-\u9fa5]");
    public static Pattern IF_INCLUDE_SYMBOL = Pattern.compile("[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
    public static Pattern MAIL_IF_INCLUDE_SYMBOL = Pattern.compile("[`~!#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]");
}
