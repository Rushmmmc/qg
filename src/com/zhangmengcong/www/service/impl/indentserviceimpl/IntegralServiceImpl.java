package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.service.service.indentservice.IntegralService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function: 使用积分
 * @date: 2020/5/3 14:29
 */
public class IntegralServiceImpl implements IntegralService {
    @Override
    public boolean useIntegralService(int integral,String username) {
        Factory factory = new Factory();
        int actuallyIntegral = Integer.parseInt(factory.getQueryDao().queryDao("integral","user","username","\""+username+"\""));
        if(actuallyIntegral >= integral){
            factory.getUpdateDao().updateDao("user","integral",String.valueOf(actuallyIntegral-integral),null,null,"username","\""+username+"\"");
            return true;
        }else {
            return false;
        }
    }
}
