package com.zhangmengcong.www.controller;

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
    Factory factory = new Factory();
    /**
     * 管理员封禁或解封用户
     */
    public void ban(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //检测是封禁还是解封
        int ifBan = Integer.parseInt(request.getParameter("operate"));
        String banUsername = request.getParameter("username");
        String banReason = request.getParameter("reason");

        //检验数据格式 返回提示信息
        String message = factory.getAdminBanOrUnbanUserService().adminBanOrUnbanUserService(ifBan, banUsername, banReason);
        request.setAttribute("goodsList", factory.getGoodsPrintService().goodsPrintService());
        response.getWriter().write(message);
    }

    /**
     * 更改用户的等级 用于测试
     */
    public void changeLevel(HttpServletRequest request, HttpServletResponse response) throws IOException {
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

    /**
     * 管理员封禁或解封商品
     */
    public void banOrUnbanGoods (HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        int ifBan = Integer.parseInt(request.getParameter("ifBan"));
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        String message = factory.getBanOrUnbanGoodsService().banOrUnbanGoodsService(ifBan,goodsId);
        System.out.println(ifBan+goodsId+message);
        response.getWriter().write(message);
    }
}