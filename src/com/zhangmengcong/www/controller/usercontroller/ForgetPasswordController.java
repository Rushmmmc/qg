package com.zhangmengcong.www.controller.usercontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:zmc
 * @function: 用户忘记密码
 * @date: 2020/5/1 14:00
 */
@WebServlet("/ForgetPasswordController")
public class ForgetPasswordController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String address = request.getParameter("address");
        Factory factory = new Factory();
            //在service检测是否存在该用户 发送邮件 并返回提示信息
          request.setAttribute("message",factory.getForgetPasswordService().sendMail(address));
          request.getRequestDispatcher("/forget.jsp").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
