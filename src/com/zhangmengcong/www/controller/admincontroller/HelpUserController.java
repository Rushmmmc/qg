package com.zhangmengcong.www.controller.admincontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/3 19:19
 */
@WebServlet("/HelpUserController")
public class HelpUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        request.setAttribute("appealList",factory.getPrintAppealService().printAppealServiceImpl());
        try {
            request.getRequestDispatcher("helpUser").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
