package com.zhangmengcong.www.controller.admincontroller;

import com.zhangmengcong.www.controller.BaseServlet;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.UserConstant.CHANGE_LEVEL_FAIL;


/**
 * @author:zmc
 * @function: 管理员禁止用户出售二手商品
 * @date: 2020/5/1 17:39
 */
@WebServlet("/admin/*")
public class AdminController extends BaseServlet {
    public void ban(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    public void changeLevel(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int level = Integer.parseInt(request.getParameter("level"));
        String username = (String) session.getAttribute("username");


        //检验数据格式 判空 长度
        String message = factory.getBecomeAdminService().becomeAdminServiceImpl(level, username);

        //若用户填写的等级正确才能改
        if (!message.contains(CHANGE_LEVEL_FAIL)) {
            session.setAttribute("level", level);
            session.setAttribute("sendLevel", factory.getEstimateStatus().estimateStatus(level));
        }
        try {
            request.getRequestDispatcher("/DividePageController").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}