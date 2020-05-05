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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        //设置编码防止乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //设置编码防止乱码
        //获取区域 从前端获取各种信息
        String mailAddress = request.getParameter("mailaddress");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String captcha = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String captchar = (String) session.getAttribute("captcha");

        //创建工厂对象
        Factory factory = new Factory();


        //实现注册功能
        //获得注册服务返回的信息 service层进行数据判空 判断是否包含中文与特殊符号
        String message = factory.getRegisterAndLogin().register(username,password,mailAddress,captchar,captcha);
            request.setAttribute("message",message);
                try {
                    //注册成功跳转至登录页面
                    if(message.contains(REGISTER_SUCCESS)){
                        request.getRequestDispatcher("/login.jsp").forward(request,response);
                    }else {
                        request.getRequestDispatcher("/Register.jsp").forward(request,response);
                    }
                } catch (ServletException e) {
                    e.printStackTrace();
                }
        //实现注册功能
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request,response);
    }
}
