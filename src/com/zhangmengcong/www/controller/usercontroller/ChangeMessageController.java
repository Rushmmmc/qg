package com.zhangmengcong.www.controller.usercontroller;

import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static com.zhangmengcong.www.constant.UserConstant.*;

/**
 * @author:zmc
 * @function: 用于页面跳转
 * @date 2020/4/29 20:31
 */
@WebServlet("/ChangeMessageController")
public class ChangeMessageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        Factory factory = new Factory();
        User user = new User();
        HttpSession session = request.getSession();

        //从前端接收用户的新信息
        String username = (String)session.getAttribute("username");
        String newUsername = request.getParameter("newusername");
        user.setPassword(request.getParameter("newpassword"));
        user.setUsername(newUsername);
        user.setMailAddress(request.getParameter("newaddress"));

        //调用修改方法 检验数据格式 返回提示信息
        String message = factory.getChangeMessageService().getChangeMessageServiceImpl(user,username);
            //修改cookie和session否则会出错
            session.setAttribute("username",newUsername);
            Cookie cookie = new Cookie("username", newUsername);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
            request.setAttribute("message",message);
            request.setAttribute("emps",factory.getPrintTableService().selectPersonalMessage(username));
            request.getRequestDispatcher("/change.jsp").forward(request,response);
        //实现修改个人信息功能

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    this.doPost(request,response);
    }
}
