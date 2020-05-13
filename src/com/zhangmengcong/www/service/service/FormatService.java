package com.zhangmengcong.www.service.service;

/**
 * @author:zmc
 * @function: 正则判断信息是否为空 是否包含中文 是否包含特殊符号
 * @date: 2020/5/4 9:48
 */
public interface FormatService {
    /** 检测是否存在中文与特殊符号
     *
     * @param message 要检验的字符
     * @return 是否符合格式
     */
   boolean formatService(String message);

    /** 检测邮箱的格式
     *
     * @param message 邮箱
     * @return 邮箱是否包含中文 特殊符号
     */
   boolean mailFormatService(String message);

    /** 仅检测是否存在特殊符号
     *
     * @param message 信息
     * @return 返回是否存在特殊符号
     */
    boolean ifIncludeSymbol(String message);

    /** 把常见的.?。等符号替换为空再检测
     *
     * @param message 需要替换的信息
     * @return 替换后的字符串
     */
    String replaceNormalSymbol(String message);

    /** 检测小数是否超过两位
     *
     * @param message 检测的数据
     * @return 超过两位则返回true
     */
    boolean ifRadixPointLessTwo(String message);

    /** 检查订单id
     *
     * @param indentId 订单id
     * @return 订单id格式是否有误
     */
    boolean ifIdFormatWrong(String indentId);
}
