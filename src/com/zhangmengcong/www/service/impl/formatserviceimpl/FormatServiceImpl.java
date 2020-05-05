package com.zhangmengcong.www.service.impl.formatserviceimpl;

import com.zhangmengcong.www.service.service.formatservice.FormatService;

import java.util.regex.Matcher;

import static com.zhangmengcong.www.constant.FormatConstant.*;

/**
 * @author:zmc
 * @function: 正则判断信息是否为空 是否包含中文 是否包含特殊符号
 * @date: 2020/5/4 9:48
 */
public class FormatServiceImpl implements FormatService {


    @Override
    public boolean formatService(String message) {
        if(message == null || message.length() == 0){
            return true;
        }
        Matcher messageChineseMatcher = IF_INCLUDE_CHINESE_PATTERN.matcher(message);
        Matcher messageSymbolMatcher = IF_INCLUDE_SYMBOL.matcher(message);
        return messageChineseMatcher.matches() || !messageSymbolMatcher.matches();
    }

    @Override
    public boolean mailFormatService(String message) {
        if(message == null || message.length() == 0){
            return true;
        }
        Matcher messageChineseMatcher = IF_INCLUDE_CHINESE_PATTERN.matcher(message);
        Matcher messageSymbolMatcher = IF_MAIL_FORMAT_CORRECT.matcher(message);
        return messageChineseMatcher.matches() || !messageSymbolMatcher.matches() || !message.contains(".com")
                || !message.contains("@");
    }

    @Override
    public boolean ifIncludeSymbol(String message){
        if(message == null || message.length() == 0){
            return true;
        }

        Matcher messageSymbolMatcher = IF_INCLUDE_SYMBOL.matcher(message);
        return !messageSymbolMatcher.matches();
    }
}
