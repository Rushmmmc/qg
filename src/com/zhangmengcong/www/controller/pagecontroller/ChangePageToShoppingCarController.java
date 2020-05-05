package com.zhangmengcong.www.controller.pagecontroller;

import com.zhangmengcong.www.util.Factory;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.IndentConstant.*;
import static com.zhangmengcong.www.constant.PageConstant.BUYER_FUNCTION;
/**
 * @author:zmc
 * @function:
 * @date: 2020/5/2 23:44
 */
@WebServlet("/ChangePageToShoppingCarController")
public class ChangePageToShoppingCarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        Factory factory = new Factory();
        //选择打印买家订单功能
        request.setAttribute("emps",factory.getPrintIndentService().printIndentService(username,BUYER_FUNCTION,SHOPPING_CAR_FUNCTION));
        try {
            request.getRequestDispatcher("/shoppingCar.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
