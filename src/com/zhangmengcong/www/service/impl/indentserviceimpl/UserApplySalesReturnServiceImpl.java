package com.zhangmengcong.www.service.impl.indentserviceimpl;

import com.zhangmengcong.www.po.Appeal;
import com.zhangmengcong.www.service.service.indentservice.UserApplySalesReturnService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.IndentConstant.*;
import static com.zhangmengcong.www.constant.UserConstant.INDENT_HAVNOT_START;
import static com.zhangmengcong.www.constant.UserConstant.USER_APPLYING_RETURN;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/8 22:49
 */
public class UserApplySalesReturnServiceImpl implements UserApplySalesReturnService {


   @Override
   public String returnGoodsService(int ifSeller, int indentId, String type) {
      if(ifSeller == IF_SELLER) {
       return   sellerManageReturnService(indentId, type);
      }else {
       return   userApplySalesReturnService(indentId);
      }

   }

   @Override
   public String userApplySalesReturnService(int indentId) {
      Factory factory = new Factory();
      String status = factory.getQueryDao().queryDao("status","indent",
              "id",String.valueOf(indentId));
      //如果还未发货
      if(status.contains(INDENT_HAVNOT_START)){
         return "只有发货后才能申请退货┭┮﹏┭┮";
      }
      boolean ifIndentIdFormatWrong = factory.getFormatService().formatService(String.valueOf(indentId));
      if(ifIndentIdFormatWrong){
         return "订单id格式不正确！┭┮﹏┭┮";
      }

      //无误 进行修改订单状态 提交给商家处理退货
      factory.getUpdateDao().updateDao("indent","status",
              "'用户申请退货'","id",String.valueOf(indentId));
   return  "处理成功";
   }

   @Override
   public String sellerManageReturnService(int indentId,String type) {
      Factory factory = new Factory();
      String status = factory.getQueryDao().queryDao("status","indent",
              "id",String.valueOf(indentId));
      //如果不是申请退货中
      if(!status.contains(USER_APPLYING_RETURN)){
         return "只有申请退货中才能处理退货┭┮﹏┭┮";
      }
      boolean ifIndentIdFormatWrong = factory.getFormatService().formatService(String.valueOf(indentId));
      if(ifIndentIdFormatWrong){
         return "订单id格式不正确！┭┮﹏┭┮";
      }
      //无误 进行处理修改订单状态
      //如果同意退货
      String buyerName = factory.getQueryDao().queryDao("buyer","indent"
              ,"id",String.valueOf(indentId));
      if(AGREE_RETURN.equals(type)){
         factory.getUpdateDao().updateDao("indent","status",
                 "'商家已退货'","id",String.valueOf(indentId));
         int integral = Integer.parseInt(factory.getQueryDao().queryDao("useIntegral","indent"
                 ,"id",String.valueOf(indentId)));

         //返回积分
         factory.getUpdateDao().updateDao("user","integral",
                 "integral+"+integral,"username","\""+buyerName+"\"");
         return "已退货,并返还客户积分"+integral;
      }
      if(REJECT_RETURN.equals(type)){
         factory.getUpdateDao().updateDao("indent","status",
                 "'商家拒绝退货,闲鱼小二介入处理中'","id",String.valueOf(indentId));
        //生成申诉单 让小二处理
         String  sellerName = factory.getQueryDao().queryDao("seller","indent",
                 "id",String.valueOf(indentId));

         Appeal appeal = new Appeal();
         appeal.setSeller(sellerName);
         appeal.setUsername(buyerName);
         appeal.setType("交易维权");
         appeal.setReason("申请退货");
         appeal.setIdentId(indentId);
         factory.getGenerateAppealService().generateAppealService(appeal);
         return "QG小二正在介入处理";
      }
      return "处理成功";
   }
}
