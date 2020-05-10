package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.service.service.indentservice.UserEvaluateIndentService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/10 13:34
 */
public class UserEvaluateIndentServiceImpl implements UserEvaluateIndentService {
    @Override
    public String userEvaluateIndent(String evaluate, int indentId) {
        Factory factory = new Factory();

        boolean ifEvaluateFormatWrong = factory.getFormatService().ifIncludeSymbol(evaluate);
        if(ifEvaluateFormatWrong){
            return "评价文字不可包含特殊符号┭┮﹏┭┮";
        }
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(indentId));
        if(ifIdFormatWrong){
            return "id仅支持整数┭┮﹏┭┮";
        }
        factory.getUpdateDao().updateDao("indent","evaluate","\""+evaluate+"\"","id",String.valueOf(indentId));
        return "评价成功( •̀ ω •́ )y";
    }
}
