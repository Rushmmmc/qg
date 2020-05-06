package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:zmc
 * @function: 审核通过 或 删除商品
 * @date: 2020/5/1 16:43
 */
@WebServlet("/GoodsDeleteOrPassController")
public class GoodsDeleteOrPassController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        //检测管理员希望审核还是删除商品
        int ifDelete = Integer.parseInt(request.getParameter("ifDelete"));
        int id = Integer.parseInt(request.getParameter("id"));
        Factory factory = new Factory();
           String message =  factory.getDeleteOrPassGoodsService().deleteOrPassGoodsService(id,ifDelete);
           response.getWriter().write(message);
//        request.setAttribute("goodsList",factory.getGoodsPrintService().goodsPrintService());
//        request.getRequestDispatcher("/manageGoods.jsp").forward(request,response);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request,response);
    }
}
