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
        //检测是否使用购物车功能
        int ifShoppingCar = Integer.parseInt(request.getParameter("ifShoppingCar"));
        //如果使用购物车功能 把订单状态设为购物车
        if(ifShoppingCar == IF_SHOPPINGCAR){
            request.setAttribute("message",SHOPPING_CAR_MESSAGE);
            indent.setAmount(1);
        }else {
                 indent.setAmount(Integer.parseInt(request.getParameter("tempAmount")));
                 request.setAttribute("message", BUY_GOODS_SUCCESS);
             }
            //单价
            indent.setPrice(Integer.parseInt(request.getParameter("tempPrice")));
            indent.setBuyer((String) session.getAttribute("username"));
            indent.setGoodsName(request.getParameter("tempGoodsName"));
            indent.setSeller(request.getParameter("tempSeller"));

            factory.getBuyGoodsService().sellGoodsService(indent,ifShoppingCar);
        try {
            request.getRequestDispatcher("/DividePageController").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
    this.doPost(request,response);
    }
}
