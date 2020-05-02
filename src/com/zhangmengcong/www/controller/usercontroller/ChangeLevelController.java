package com.zhangmengcong.www.controller.usercontroller;

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
 * @function: 给用户升级 用于测试管理员功能
 * @date:2020/4/29 22:50
 */
@WebServlet("/ChangeLevelController")
public class ChangeLevelController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        Factory factory = new Factory();
        HttpSession session = request.getSession();
        int level = Integer.parseInt(request.getParameter("level"));
        String username = (String)session.getAttribute("username");
        factory.getBecomeAdminService().becomeAdminServiceImpl(3,username);
        session.setAttribute("level", level);
        session.setAttribute("sendLevel", factory.getEstimateStatus().estimateStatus(3));
        try {
            request.getRequestDispatcher("/change.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    this.doPost(request,response);
    }
}
