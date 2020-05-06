package com.zhangmengcong.www.controller.admincontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



/**
 * @author:zmc
 * @function: 管理员禁止用户出售二手商品
 * @date: 2020/5/1 17:39
 */
@WebServlet("/AdminBanUserController")
public class AdminBanUserController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Factory factory = new Factory();

        //检测是封禁还是解封
        int ifBan = Integer.parseInt(request.getParameter("operate"));
        String banUsername = request.getParameter("username");
        String banReason = request.getParameter("reason");

        //检验数据格式 返回提示信息
        String message = factory.getAdminBanOrUnbanUserService().adminBanOrUnbanUserService(ifBan, banUsername, banReason);
        request.setAttribute("goodsList", factory.getGoodsPrintService().goodsPrintService());
        response.getWriter().write(message);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws   IOException {
    this.doPost(request,response);
    }
}
