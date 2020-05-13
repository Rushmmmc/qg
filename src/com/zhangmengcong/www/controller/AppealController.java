package com.zhangmengcong.www.controller;


import com.zhangmengcong.www.controller.BaseServlet;
import com.zhangmengcong.www.po.Appeal;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author:zmc
 * @function: 处理申诉
 * @date: 2020/5/3 19:19
 */

@WebServlet("/AppealController/*")
    public class AppealController extends BaseServlet {public void manageAppeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String type = request.getParameter("type");
        int id = Integer.parseInt(request.getParameter("id"));
        int appealId = Integer.parseInt(request.getParameter("appealId"));
        //处理申诉 判断数据格式 并返回提示信息
        String message = factory.getManageAppealService().manageAppealService(type,id,appealId);
        response.getWriter().write(message);
    }

    /**
     * 用户提交申诉
     */
    public void commitAppeal(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Appeal appeal = new Appeal();

        HttpSession session = request.getSession();
        //获取申诉信息
        appeal.setUsername((String)session.getAttribute("username"));
        appeal.setIdentId(Integer.parseInt(request.getParameter("indentId")));
        appeal.setType(request.getParameter("type"));
        appeal.setReason(request.getParameter("reason"));

        //调用方法生成申诉 验证信息格式 并返回提示信息
        String message = factory.getGenerateAppealService().generateAppealService(appeal);
        response.getWriter().write(message);
    }
}
