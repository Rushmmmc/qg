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

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
