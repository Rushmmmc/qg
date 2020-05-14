package com.zhangmengcong.www.service.impl;

import com.zhangmengcong.www.service.service.DeleteOrPassGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_DELETE;
import static com.zhangmengcong.www.constant.UserConstant.ADMIN_LEVEL;
import static com.zhangmengcong.www.constant.UserConstant.USER_LEVEL;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 16:39
 */
public class DeleteOrPassGoodsServiceImpl implements DeleteOrPassGoodsService {
    Factory factory = new Factory();
    @Override
   public void deleteGoodsService(int id){
       factory.getDeleteOrChangeDao().deleteOrChange("goods",IF_DELETE,id,null,null,false,null);
   }

    @Override
    public void passGoodsService(int id) {
        factory.getDeleteOrChangeDao().deleteOrChange("goods",0,id,"已审核","status",false,null);
    }

    @Override
    public String deleteOrPassGoodsService(int id, int ifDelete,String username) {
        //检测是否管理员 拦截游客和用户
        int level = Integer.parseInt(factory.getQueryDao().queryDao("level", "user", "username",
                "\"" + username + "\""));
        if (level != ADMIN_LEVEL) {
            return "只有用户可以进行此操作";
        }
        if (ifDelete == IF_DELETE) {
            deleteGoodsService(id);
            return "删除成功！";
        } else {
            passGoodsService(id);
            return "商品已通过!";
        }
    }

    @Override
    public String sellerDeleteGoods(int goodsId,String sellerName) {
        //先检测该商品格式是否正确
        boolean ifGoodsNameFormatWrong = factory.getFormatService().formatService(String.valueOf(goodsId));
        if (ifGoodsNameFormatWrong){
            return "商品名格式错误！";
        }
        //检测该商品是否该用户的
        String tempSellerName = factory.getQueryDao().queryDao("seller","goods"
        ,"id",String.valueOf(goodsId));
        if(!tempSellerName.equals(sellerName)){
            return "该商品不是您的商品┭┮﹏┭┮,无法删除┭┮﹏┭┮";
        }
        //无误 删除
        factory.getDeleteOrPassGoodsService().deleteGoodsService(goodsId);
        return "删除成功！";
    }
}
