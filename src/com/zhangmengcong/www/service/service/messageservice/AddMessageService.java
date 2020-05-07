package com.zhangmengcong.www.service.service.messageservice;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.po.Message;

/**
 * @author:zmc
 * @function: 关于留言板的服务
 * @date: 2020/5/6 22:53
 */
public interface AddMessageService {
    /**
     *
     * @param id 信息id
     * @return 提示信息
     */
    String deleteMessageService(int id);

    /**
     *
     * @param message 新增的message对象
     * @return 提示信息
     */
    String addMessageService(Indent indent, Message message);
}
