package com.zhangmengcong.www.controller.usercontroller;


import com.zhangmengcong.www.util.Factory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:zmc
 * @function: 用户给好评或差评
 * @date: 2020/5/3 13:19
 */
@WebServlet("/GiveReputationController")
public class GiveReputationController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        int id = Integer.parseInt(request.getParameter("id"));
        factory.getIndentService().indentSelectMethod(method,id,null,null,null);
        response.getWriter().write("感谢您的评价O(∩_∩)O");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
