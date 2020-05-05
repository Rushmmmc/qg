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
import static com.zhangmengcong.www.constant.IndentConstant.DONOT_NEED_SO_MUCH_INTEGRAL;
import static com.zhangmengcong.www.constant.IndentConstant.INTEGRAL_NOT_ENOUGN;

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
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        //获取处理途径 在service分判方法
        String method = request.getParameter("method");
        Indent indent = new Indent();

        int id = Integer.parseInt(request.getParameter("id"));
        int price = Integer.parseInt(request.getParameter("price"));
        int integral = Integer.parseInt(request.getParameter("integral"));
        //若用户使用积分大于价格 提示用户
        if(integral > price){
            request.setAttribute("message",DONOT_NEED_SO_MUCH_INTEGRAL);
        }
        //查询已有积分 若足够 扣除 不够则提示用户并不生成订单
        else if(factory.getIntegralService().useIntegralService(integral,username)){
            //设置订单信息
            indent.setPrice(price);
            indent.setAmount(Integer.parseInt(request.getParameter("amount")));
            indent.setUseIntegral(Integer.parseInt(request.getParameter("integral")));

            request.setAttribute("message",BUY_GOODS_SUCCESS);
            factory.getIndentService().indentSelectMethod(method,id,indent,null,null);
        }else{
            request.setAttribute("message",INTEGRAL_NOT_ENOUGN);
        }
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
