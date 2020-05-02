package com.zhangmengcong.www.controller.usercontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.UserConstant.*;

/**
 * @author:zmc
 * @function: 用于用户注册
 * @date: 2020/4/29 9:21
 */
@WebServlet("/Register")
public class RegisterController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码防止乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //设置编码防止乱码
        //获取区域 从前端获取各种信息 method的存在使得一个Servlet可以做很多事
        String mailaddress = request.getParameter("mailaddress");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String captcha = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String captchar = (String) session.getAttribute("captcha");

        //创建工厂对象
        Factory factory = new Factory();
          if(username.length() < NAME_LENGTH || password.length() < PASSWORD_LENGTH || mailaddress.length() < MAIL_LENGTH ||
                captcha.length() < CAPTCHA_LENGTH ){
            request.setAttribute("message",MESSAGE_WRONG);
            request.getRequestDispatcher("/RegisterController.jsp").forward(request,response);
        }

        //实现注册功能

        if(!factory.getRegisterAndLogin().register(username,factory.getEncode().shaEncode(password),mailaddress,captchar,captcha)){
            request.setAttribute("message",MESSAGE_HAVE_BEEN_OCCUPIED);
            request.getRequestDispatcher("/Register.jsp").forward(request,response);
        }else {
            request.setAttribute("message", REGISTER_SUCCESS);
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
        //实现注册功能
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
