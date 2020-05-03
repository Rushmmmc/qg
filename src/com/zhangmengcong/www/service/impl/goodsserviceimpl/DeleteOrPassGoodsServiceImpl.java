package com.zhangmengcong.www.service.impl.goodsserviceimpl;

import com.zhangmengcong.www.service.service.goodsservice.DeleteOrPassGoodsService;
import com.zhangmengcong.www.util.Factory;

import static com.zhangmengcong.www.constant.GoodsConstant.IF_DELETE;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 16:39
 */
public class DeleteOrPassGoodsServiceImpl implements DeleteOrPassGoodsService {
   @Override
   public void deletGoodsService(int id){
       Factory factory = new Factory();
        factory.getDeleteOrChangeDao().deleteOrChange("goods",IF_DELETE,id,null,null,false,null);
   }

    @Override
    public void passGoodsService(int id) {
        Factory factory = new Factory();
        factory.getDeleteOrChangeDao().deleteOrChange("goods",0,id,"已审核","status",false,null);
    }

    @Override
    public void deleteOrPassGoodsService(int id, int ifDelete){
       if(ifDelete == IF_DELETE){
           deletGoodsService(id);
       }else {
           passGoodsService(id);
       }
    }


}
