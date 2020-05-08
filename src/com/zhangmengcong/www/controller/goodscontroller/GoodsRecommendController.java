package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author:zmc
 * @function: 选择用户评价过的文字作为首页推荐商品的信息
 * @date: 2020/5/8 1:21
 */
@WebServlet("/GoodsRecommendController")
public class GoodsRecommendController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String recommend = request.getParameter("recommend");
        int indentId = Integer.parseInt(request.getParameter("indentId"));

        String message = factory.getGoodsRecommendService().chooseGoodsRecommendService(recommend,indentId);
        response.getWriter().write(message);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
