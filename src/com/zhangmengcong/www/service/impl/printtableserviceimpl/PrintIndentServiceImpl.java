package com.zhangmengcong.www.service.impl.printtableserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.printtableservice.PrintIndentService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SHOPPING_CAR;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 20:21
 */
public class PrintIndentServiceImpl implements PrintIndentService {
    @Override
    public List<Indent> printIndentService(String username,int ifSeller,int ifShoppingCar) {
        Factory factory = new Factory();
        boolean tempIfShoppingCar = false;
        if(ifShoppingCar == IF_SHOPPING_CAR){
            tempIfShoppingCar = true;
        }
        return factory.getIndentPrintDao().selectPersonalIndent(username,ifSeller,tempIfShoppingCar);
    }
}
