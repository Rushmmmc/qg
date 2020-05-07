package com.zhangmengcong.www.service.impl.printtableserviceimpl;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.service.service.printtableservice.PrintIndentService;
import com.zhangmengcong.www.util.Factory;

import java.util.List;

import static com.zhangmengcong.www.constant.IndentConstant.SHOPPING_CAR_FUNCTION;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 20:21
 */
public class PrintIndentServiceImpl implements PrintIndentService {
    @Override
    public List<Indent> printIndentService(int id,String username,int ifSeller,int ifShoppingCar) {
        Factory factory = new Factory();
        boolean tempIfShoppingCar = false;
        if(ifShoppingCar == SHOPPING_CAR_FUNCTION){
            tempIfShoppingCar = true;
        }
        return factory.getIndentPrintDao().selectPersonalIndent(id,username,ifSeller,tempIfShoppingCar);
    }
}
