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
 * @function:
 * @date: 2020/5/2 16:09
 */
@WebServlet("/IndentSendMessageController")
public class IndentSendMessageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        String message = request.getParameter("message");
        int id = Integer.parseInt(request.getParameter("id"));
        factory.getIndentService().indentSelectMethod(method,id,null,message,null);
        request.setAttribute("message",SEND_MESSAGE_SUCCESS);
        try {
            //卖商品则去往商家管理商品页面
            if(SELLER_SEND_MESSAGE.equals(method)){
                request.getRequestDispatcher("/ChangePageController?method="+MANAGE_INDENT).forward(
                        request,response);
            }
            //否则去往用户管理个人订单页面
            else {
                request.getRequestDispatcher("/ChangePageController?method=" + MANAGE_BUYER_PERSONAL_INDENT).forward(
                        request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
