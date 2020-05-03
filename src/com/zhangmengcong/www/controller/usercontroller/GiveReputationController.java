package com.zhangmengcong.www.controller.usercontroller;

import com.zhangmengcong.www.controller.pagecontroller.ChangePageController;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zhangmengcong.www.constant.GoodsConstant.*;
import static com.zhangmengcong.www.constant.PageConstant.MANAGE_BUYER_PERSONAL_INDENT;

/**
 * @author:zmc
 * @function: 用户给好评或差评
 * @date: 2020/5/3 13:19
 */
@WebServlet("/GiveReputationController")
public class GiveReputationController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        String method = request.getParameter("method");
        int id = Integer.parseInt(request.getParameter("id"));
        factory.getIndentService().indentSelectMethod(method,id,null,null,null);
        try {
            if(GOOD_REPUTATION.equals(method)){
                request.setAttribute("message",GOOD_REPUTATION_MESSAGE);
            }else {
                request.setAttribute("message",BAD_REPUTATION_MESSAGE);
            }
            request.getRequestDispatcher("/ChangePageController?method="+MANAGE_BUYER_PERSONAL_INDENT).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
