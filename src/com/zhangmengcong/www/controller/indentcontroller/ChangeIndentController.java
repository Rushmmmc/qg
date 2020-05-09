package com.zhangmengcong.www.controller.indentcontroller;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.Factory;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.GoodsConstant.SELL;
import static com.zhangmengcong.www.constant.IndentConstant.CHANGE_INDENT;

/**
 * @author:zmc
 * @function: 商家改变订单信息 用户清除购物车 选择完成订单/取消订单/修改订单的服务
 * @date: 2020/5/2 13:30
 */
@WebServlet("/ChangeIndentController")
public class ChangeIndentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
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
        }
        if(SELL.equals(method)){
            indent.setAmount(Integer.parseInt(request.getParameter("amount")));
        }
        indent.setGoodsName(request.getParameter("goodsName"));

        //自动选择 完成订单/取消订单/修改订单 方法 非常智能
        String message = factory.getIndentService().indentSelectMethod(method,id,indent,null,username);
        response.getWriter().write(message);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    this.doPost(request,response);
    }
}
