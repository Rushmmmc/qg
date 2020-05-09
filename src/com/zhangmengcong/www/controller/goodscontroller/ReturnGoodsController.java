package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/8 23:24
 */
@WebServlet("/ReturnGoodsController")
public class ReturnGoodsController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String type = "";
        //接收订单号 处理方式 ifSeller = 1 即为商家处理退货
        int ifSeller = Integer.parseInt(request.getParameter("ifSeller"));
        int indentId = Integer.parseInt(request.getParameter("indentId"));
        //如果是商家处理退货
        if(ifSeller == IF_SELLER){
            type = request.getParameter("type");
        }
        //否则为用户申请退货
        //传入参数 获取提示信息
        String message = factory.getUserApplySalesReturnService()
                .returnGoodsService(ifSeller,indentId,type);
        response.getWriter().write(message);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
