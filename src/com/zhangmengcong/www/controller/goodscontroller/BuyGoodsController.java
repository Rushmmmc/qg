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

import static com.zhangmengcong.www.constant.GoodsConstant.BUY_GOODS_SUCCESS;
import static com.zhangmengcong.www.constant.PageConstant.MANAGE_BUYER_PERSONAL_INDENT;

/**
 * @author:zmc
 * @function: 用户买商品 生成订单
 * @date: 2020/5/1 21:24
 */
@WebServlet("/BuyGoodsController")
public class BuyGoodsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Factory factory = new Factory();
        Indent indent = new Indent();
        HttpSession session = request.getSession();
        //单价
            indent.setPrice(Integer.parseInt(request.getParameter("tempPrice")));
            indent.setBuyer((String) session.getAttribute("username"));
            indent.setGoodsName(request.getParameter("tempGoodsName"));
            indent.setSeller(request.getParameter("tempSeller"));
            indent.setAmount(Integer.parseInt(request.getParameter("tempAmount")));
            factory.getBuyGoodsService().sellGoodsService(indent);
            request.setAttribute("message",BUY_GOODS_SUCCESS);
        try {
            request.getRequestDispatcher("/ChangePageController?method="+MANAGE_BUYER_PERSONAL_INDENT).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
    this.doPost(request,response);
    }
}
