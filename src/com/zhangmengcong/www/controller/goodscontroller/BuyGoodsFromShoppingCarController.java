package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zhangmengcong.www.constant.GoodsConstant.BUY_GOODS_SUCCESS;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/3 0:16
 */
@WebServlet("/BuyGoodsFromShoppingCarController")
public class BuyGoodsFromShoppingCarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        Indent indent = new Indent();
        int id = Integer.parseInt(request.getParameter("id"));
        indent.setAmount(Integer.parseInt(request.getParameter("amount")));
        indent.setPrice(Integer.parseInt(request.getParameter("price")));
        String method = request.getParameter("method");

        factory.getIndentService().indentSelectMethod(method,id,indent,null,null);

        request.setAttribute("message",BUY_GOODS_SUCCESS);
        try {
            request.getRequestDispatcher("/ChangePageToShoppingCarController").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
