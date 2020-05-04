package com.zhangmengcong.www.service.impl.adminimpl;

import com.zhangmengcong.www.service.service.adminservice.ManageAppealService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.AdminConstant.*;

/**
 * @author:zmc
 * @function: 扣除被投诉商家的信誉分
 * @date: 2020/5/3 21:06
 */
public class ManageAppealServiceImpl implements ManageAppealService {
    @Override
    public void minusSellerReputationService(String username,int id) {
        Factory factory = new Factory();
        factory.getUpdateDao().updateDao("user","reputationPoint","reputationPoint - 1",
                null,null,"username","\"" + username + "\"");
    }

    @Override
    public String manageAppealService(String type,int id) {
        Factory factory = new Factory();
        String message = "";
        String username = factory.getQueryDao().queryDao("seller","indent","id",String.valueOf(id));
        //处理投诉商家
        if(COMPLAINT_SELLER.equals(type)){
            minusSellerReputationService(username,id);
            message =   MINUS_SUCCESS_MESSAGE;
        }
        //处理交易维权
        if(DEFEND_LEGAL_RIGHT.equals(type)){
            int actuallyPrice = Integer.parseInt(factory.getQueryDao().queryDao("actuallyPrice","indent","id",String.valueOf(id)));
            int integral = 2 * Integer.parseInt(factory.getQueryDao().queryDao("useIntegral","indent","id",String.valueOf(id)));
            factory.getUpdateDao().updateDao("user","integral","integral + "+ integral,null,null,"username","\""+username+"\"");
            message =  "已退款"+actuallyPrice+"元 返还使用的双倍积分共 "+integral +"分";
        }
    return message;
    }
}
