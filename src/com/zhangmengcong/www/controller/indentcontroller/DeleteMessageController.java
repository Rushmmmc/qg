package com.zhangmengcong.www.controller.indentcontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;
import static com.zhangmengcong.www.constant.IndentConstant.SELLER_DELETE_MESSAGE;
import static com.zhangmengcong.www.constant.PageConstant.*;

/**
 * @author:zmc
 * @function: 用户或商家删除留言板的信息
 * @date: 2020/5/2 17:43
 */
@WebServlet("/DeleteMessageController")
public class DeleteMessageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        int ifSeller = Integer.parseInt(request.getParameter("ifSeller"));
        int id = Integer.parseInt(request.getParameter("id"));
        int indentId = (int)session.getAttribute("indentId");
        factory.getDeleteMessageService().deleteMessageServiceImpl(id,indentId,ifSeller);
        try {
            if(ifSeller != IF_SELLER) {
                request.getRequestDispatcher("/ChangePageController?method=messageBoard&ifSeller=0&id=" + indentId).forward(request, response);
            }else {
                request.getRequestDispatcher("/ChangePageController?method=messageBoard&ifSeller=1&id=" + indentId).forward(request,response);
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
