package com.zhangmengcong.www.controller.admincontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.UserConstant.CHANGE_LEVEL_FAIL;

/**
 * @author:zmc
 * @function:
 * @date: 2020/4/30 12:32
 */
@WebServlet("/AdminController")
public class AdminChangeLevelController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int level = Integer.parseInt(request.getParameter("level"));
        String username = (String)session.getAttribute("username");

        session.setAttribute("sendLevel", factory.getEstimateStatus().estimateStatus(level));
        //检验数据格式 判空 长度 返回提示信息
        String message = factory.getBecomeAdminService().becomeAdminServiceImpl(level,username);

        //若用户填写的等级正确才能改
        if(!message.contains(CHANGE_LEVEL_FAIL)){
            session.setAttribute("level",level);
        }
        request.getRequestDispatcher("/DividePageController").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
