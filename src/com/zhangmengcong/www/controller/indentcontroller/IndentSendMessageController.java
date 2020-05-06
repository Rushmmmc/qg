package com.zhangmengcong.www.controller.indentcontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zhangmengcong.www.constant.IndentConstant.SELLER_SEND_MESSAGE;
import static com.zhangmengcong.www.constant.IndentConstant.SEND_MESSAGE_SUCCESS;
import static com.zhangmengcong.www.constant.PageConstant.*;

/**
 * @author:zmc
 * @function: 给商家或用户留言的留言板
 * @date: 2020/5/2 16:09
 */
@WebServlet("/IndentSendMessageController")
public class IndentSendMessageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        String message = request.getParameter("message");
        int id = Integer.parseInt(request.getParameter("id"));
        //传入method 自动分配方法
        factory.getIndentService().indentSelectMethod(method,id,null,message,null);
        response.getWriter().write(SEND_MESSAGE_SUCCESS);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
