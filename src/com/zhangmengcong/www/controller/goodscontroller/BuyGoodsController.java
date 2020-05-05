package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.IndentConstant.DONOT_NEED_SO_MUCH_INTEGRAL;
import static com.zhangmengcong.www.constant.IndentConstant.INTEGRAL_NOT_ENOUGN;

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
        //从session获取用户名及要购买商品的信息
        String username = (String) session.getAttribute("username");
        Goods goods = (Goods)session.getAttribute("goods");
        goods.setAmount(Integer.parseInt(request.getParameter("tempAmount")));

        boolean ifSuccess = false;
        //获取用户希望使用的积分量
        int integral = Integer.parseInt(request.getParameter("integral"));
        //如果用户输入积分量大于价格 提示信息
        if(integral > goods.getPrice()){
            request.setAttribute("message",DONOT_NEED_SO_MUCH_INTEGRAL);
        }
        //查询已有积分 若足够 扣除 不够则提示用户并不生成订单
        else if(factory.getIntegralService().useIntegralService(integral,username)){
            ifSuccess = true;
            indent.setAmount(Integer.parseInt(request.getParameter("tempAmount")));
            //设置订单信息
            indent.setGoodsType(goods.getType());
            indent.setUseIntegral(integral);
            indent.setBuyer(username);
            indent.setPrice(goods.getPrice());
            indent.setGoodsName(goods.getGoodsName());
            indent.setSeller(goods.getSeller());

            //验证数据格式并返回提示信息
            String message = factory.getBuyGoodsService().buyGoodsService(indent,0);
            request.setAttribute("message", message);
        }else {
            request.setAttribute("message",INTEGRAL_NOT_ENOUGN);
        }
        try {
            if(ifSuccess) {
                request.getRequestDispatcher("/DividePageController").forward(request, response);
            }else {
                request.getRequestDispatcher("/setAmount.jsp").forward(request,response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
    this.doPost(request,response);
    }
}
