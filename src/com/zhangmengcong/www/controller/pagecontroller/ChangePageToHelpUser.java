package com.zhangmengcong.www.controller.pagecontroller;

import com.zhangmengcong.www.dao.dao.goodsdao.SelectGoodsByInterest;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;
import java.io.IOException;

import static com.zhangmengcong.www.constant.UserConstant.ADMIN_LEVEL;


/**
 * @author:zmc
 * @function: 前往申诉中心
 * @date: 2020/5/3 19:19
 */
@WebServlet("/ChangePageToHelpUser")
public class ChangePageToHelpUser extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int level = (int)session.getAttribute("level");
        String username = (String) session.getAttribute("username");
        try {
            if(level == ADMIN_LEVEL) {
                request.setAttribute("appealList",factory.getPrintAppealService().printAppealServiceImpl(null));
                request.getRequestDispatcher("helpUser.jsp").forward(request, response);
            }else {
                request.setAttribute("appealList",factory.getPrintAppealService().printAppealServiceImpl(username));
                request.getRequestDispatcher("/helpMe.jsp").forward(request,response);
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
