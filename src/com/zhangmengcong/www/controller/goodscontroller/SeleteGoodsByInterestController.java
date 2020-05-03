package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * @author:zmc
 * @function: 根据用户兴趣推送商品
 * @date: 2020/5/3 17:28
 */
@WebServlet("/SeleteGoodsByInterestController")
public class SeleteGoodsByInterestController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        List<Goods> list= factory.getSelectGoodsByInterest().selectGoodsByInterest(username);
        request.setAttribute("goodsList",list);
        try {
            request.getRequestDispatcher("/ad.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
