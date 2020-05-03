package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.GoodsConstant.*;

/**
 * @author:zmc
 * @function: 把商品添加到用户的购物车
 * @date: 2020/5/3 15:40
 */
@WebServlet("/AddGoodsToShoppingCarController")
public class AddGoodsToShoppingCarController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Indent indent = new Indent();
        indent.setAmount(1);
        indent.setBuyer((String)session.getAttribute("username"));
        indent.setPrice(Integer.parseInt(request.getParameter("tempPrice")));
        indent.setGoodsName(request.getParameter("tempGoodsName"));
        indent.setSeller(request.getParameter("tempSeller"));
        indent.setGoodsType(request.getParameter("goodsTypee"));
        factory.getBuyGoodsService().sellGoodsService(indent,IF_SHOPPINGCAR);
        request.setAttribute("message",SHOPPING_CAR_MESSAGE);
        try {
            request.getRequestDispatcher("/DividePageController").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
