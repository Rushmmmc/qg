package com.zhangmengcong.www.controller.appealcontroller;

import com.zhangmengcong.www.po.Appeal;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/3 22:57
 */
@WebServlet("/UserCommitAppealController")
public class UserCommitAppealController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        Appeal appeal = new Appeal();

        HttpSession session = request.getSession();
        appeal.setUsername((String)session.getAttribute("username"));
        appeal.setIdentId(Integer.parseInt(request.getParameter("indentId")));
        appeal.setType(request.getParameter("type"));
        appeal.setReason(request.getParameter("reason"));

        //调用方法生成申诉 并返回提示信息
        String message = factory.getGenerateAppealService().generateAppealService(appeal);
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