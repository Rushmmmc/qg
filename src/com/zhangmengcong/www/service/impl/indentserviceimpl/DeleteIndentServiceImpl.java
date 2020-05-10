package com.zhangmengcong.www.service.impl.indentserviceimpl;


import com.zhangmengcong.www.service.service.indentservice.DeleteIndentService;
import com.zhangmengcong.www.util.Factory;



import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;


/**
 * @author:zmc
 * @function: 分配方法 处理订单
 * @date: 2020/5/2 12:21
 */
public class DeleteIndentServiceImpl implements DeleteIndentService {
    Factory factory = new Factory();


    @Override
    public String deleteIndent(int ifSeller, int indentId) {
        if (ifSeller == IF_SELLER) {
            return sellerDeleteIndent(indentId);
        } else {
            return buyerDeleteIndent(indentId);
        }
    }

    @Override
    public String buyerDeleteIndent(int indentId) {
        if (factory.getFormatService().ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        //用户删除订单
        factory.getUpdateDao().updateDao("indent", "ifBuyerDelete",
                "1", "id", "\"" + indentId + "\"");
        return "订单已删除！";
    }

    @Override
    public String sellerDeleteIndent(int indentId) {
        if (factory.getFormatService().ifIndentIdFormatWrong(indentId)) {
            return "订单id格式错误";
        }
        //商家删除订单
        factory.getUpdateDao().updateDao("indent", "ifSellerDelete",
                "1", "id", "\"" + indentId + "\"");
        return "订单已删除！";
    }



}







