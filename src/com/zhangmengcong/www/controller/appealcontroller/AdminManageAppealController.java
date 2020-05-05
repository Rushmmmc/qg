package com.zhangmengcong.www.controller.appealcontroller;


import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:zmc
 * @function: //处理申诉
 * @date: 2020/5/3 19:19
 */
@WebServlet("/AdminManageAppealController")
public class AdminManageAppealController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");

        String type = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("id"));

        //处理申诉 判断数据格式 并返回提示信息
        String message = factory.getManageAppealService().manageAppealService(type,id);
        request.setAttribute("message",message);
        try {
            request.getRequestDispatcher("/ChangePageToHelpUser").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
