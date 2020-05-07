package com.zhangmengcong.www.service.impl.messageserviceimpl;

import com.zhangmengcong.www.service.service.messageservice.DeleteMessageService;
import com.zhangmengcong.www.util.Factory;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/6 23:22
 */
public class DeleteMessageServiceImpl implements DeleteMessageService {
    /**
     *
     * @param id 要删除消息的id
     * @return 提示信息
     */
   @Override
   public String deleteMessageServiceImpl(int id){
        Factory factory = new Factory();
        boolean ifIdFormatWrong = factory.getFormatService().formatService(String.valueOf(id));
        if(ifIdFormatWrong){
            return "id仅支持整数┭┮﹏┭┮";
        }else {
            factory.getDeleteOrChangeDao().deleteOrChange("message",1,id,null,null,false,null);
            return "删除成功！";
        }
    }
}
