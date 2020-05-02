package com.zhangmengcong.www.service.impl.goodsservice;

import com.zhangmengcong.www.service.service.goodsservice.DeleteOrPassGoodsService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/1 16:39
 */
public class DeleteOrPassGoodsServiceImpl implements DeleteOrPassGoodsService {
   @Override
   public void deleteOrPassGoodsService(int id, int ifDelete){
       Factory factory = new Factory();
        factory.getGoodsDao().deleteOrPassGoods(ifDelete,id);
   }
}
