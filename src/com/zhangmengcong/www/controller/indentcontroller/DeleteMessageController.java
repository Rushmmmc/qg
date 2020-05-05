package com.zhangmengcong.www.controller.indentcontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        String method = request.getParameter("method");
        int id = Integer.parseInt(request.getParameter("id"));
        factory.getIndentService().indentSelectMethod(method,id,null,null,null);
        try {
            //商家去往则去往商家留言板
            if(SELLER_DELETE_MESSAGE.equals(method)) {
                request.getRequestDispatcher("/ChangePageController?method=" + MESSAGE_BORAD + "&ifSeller=1").forward(request, response);
            }else {
                request.getRequestDispatcher("/ChangePageController?method=" + MESSAGE_BORAD + "&ifSeller=0").forward(request, response);
            }
            //否则去往用户个人留言板
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
