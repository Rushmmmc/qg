package com.zhangmengcong.www.controller.indentcontroller;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.IndentConstant.CHANGE_INDENT;
import static com.zhangmengcong.www.constant.IndentConstant.DELETE_SHOPPING_CAR_INDENT;
import static com.zhangmengcong.www.constant.PageConstant.*;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/2 13:30
 */
@WebServlet("/ChangeIndentController")
public class ChangeIndentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        Factory factory = new Factory();
        String method = request.getParameter("method");
        int id = Integer.parseInt(request.getParameter("id"));
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");

        Indent indent = new Indent();
        //要改订单才获取 防止NPE
        if(CHANGE_INDENT.equals(method)) {
            indent.setId(id);
            indent.setAmount(Integer.parseInt(request.getParameter("amount")));
            indent.setPrice(Integer.parseInt(request.getParameter("price")));
            indent.setGoodsName(request.getParameter("goodsName"));
        }

        //自动选择 完成订单/取消订单/修改订单 方法 非常智能
        factory.getIndentService().indentSelectMethod(method,id,indent,null,username);

        try {
            //卖商品则去往商家管理商品页面
            if(SELL.equals(method)){
                request.getRequestDispatcher("/ChangePageController?method="+MANAGE_INDENT).forward(
                        request,response);
            }
            //去往购物车
            else if(DELETE_SHOPPING_CAR_INDENT.equals(method)){
                request.getRequestDispatcher("/ChangePageToShoppingCarController").forward(request,response);
            }
            //否则去往用户管理个人订单页面
            else {
                request.getRequestDispatcher("/ChangePageController?method=" + MANAGE_BUYER_PERSONAL_INDENT).forward(
                        request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    this.doPost(request,response);
    }
}
