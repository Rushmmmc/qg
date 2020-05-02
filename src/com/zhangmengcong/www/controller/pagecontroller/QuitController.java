package com.zhangmengcong.www.controller.pagecontroller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;

import static com.zhangmengcong.www.constant.UserConstant.USER_NAME;

/**
 * @author:zmc
 * @function: 用于注销
 * @date: 2020/4/21 20:57
 */
@WebServlet("/Quit")
public class QuitController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)  {
        HttpSession session = request.getSession();
        //实现注销功能
            if (session.getAttribute(USER_NAME) != null) {
                //session中name不存在,即session已经被销毁
                Cookie cookie = new Cookie("username","destroy");
                cookie.setMaxAge(60);
                response.addCookie(cookie);
                session.invalidate();
                //删除session即可
                try {
                    try {
                        request.getRequestDispatcher("/login.jsp").forward(request,response);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (ServletException e) {
                    e.printStackTrace();
                }
            }
        //实现注销功能
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    this.doPost(request,response);
    }

}
