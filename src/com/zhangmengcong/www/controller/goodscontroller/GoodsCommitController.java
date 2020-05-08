package com.zhangmengcong.www.controller.goodscontroller;


import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.util.Factory;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;

import static com.zhangmengcong.www.constant.GoodsConstant.ADD_GOODS_SUCCESS;


/**
 * @author:zmc
 * @function: 用于用户提交二手商品 以及管理员审核二手商品
 * @date: 2020/4/30 9:52
 */
@WebServlet("/GoodsCommitController")
public class GoodsCommitController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Factory factory = new Factory();
        Goods goods = new Goods();
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        goods.setSeller(username);
        //获取用户信誉分
        int sellerReputation = factory.getPrintTableService().selectPersonalMessage(username).get(0).getReputationPoint();
        String goodsName = request.getParameter("goodsName");
        goods.setSellerReputation(sellerReputation);
        goods.setType(request.getParameter("type"));
        goods.setGoodsName(goodsName);
        goods.setImformation(request.getParameter("imformation"));
        goods.setPrice(Float.parseFloat(request.getParameter("price")));
        goods.setAmount(Integer.parseInt(request.getParameter("amount")));

        String message = factory.getAddGoodsService().addGoodsService(goods);
        //成功后给上传图片页面存储商品名
        if(ADD_GOODS_SUCCESS.equals(message)){
            request.getSession().setAttribute("goodsName",goodsName);
        }
        response.getWriter().write(message);
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
    this.doPost(request,response);
    }
}
