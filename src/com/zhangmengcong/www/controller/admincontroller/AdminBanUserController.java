package com.zhangmengcong.www.controller.admincontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
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
        Factory factory = new Factory();
        //检测是封禁还是解封
        int ifBan = Integer.parseInt(request.getParameter("operate"));
        String banUsername = request.getParameter("username");
        String banReason = request.getParameter("reason");
        String message = factory.getAdminBanOrUnbanUserService().adminBanOrUnbanUserService(ifBan,banUsername,banReason);
        try {
            request.setAttribute("goodsList",factory.getGoodsPrintService().goodsPrintService());
            request.setAttribute("message",message);
            request.getRequestDispatcher("/manageGoods.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws   IOException {
    this.doPost(request,response);
    }
}
