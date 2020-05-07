package com.zhangmengcong.www.controller.messagecontroller;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.po.Message;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/6 23:47
 */
@WebServlet("/MessageController")
public class MessageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        //获取参数
        int ifReply = Integer.parseInt(request.getParameter("ifReply"));
        int ifSeller = Integer.parseInt(request.getParameter("ifSeller"));
        String message = request.getParameter("message");
        String tipMessage;

        //仅在回复时获取id
        if(ifReply == 1){
           int id = Integer.parseInt(request.getParameter("id"));
           //判断数据格式 判空 进行回复并生成提示信息
            int indentId = (int)session.getAttribute("indentId");
           tipMessage =  factory.getReplyMessageService().replyMessageService(ifSeller,message,id,indentId);
        }else {
            //使用留言功能
            Message message1 = new Message();

            Indent indent = ((List<Indent>)session.getAttribute("emps")).get(0);
            if(ifSeller == 1) {
                message1.setSellerMessage(message);
            }else {
                message1.setBuyerMessage(message);
            }
            tipMessage =  factory.getAddMessageService().addMessageService(indent,message1);
        }
        response.getWriter().write(tipMessage);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
