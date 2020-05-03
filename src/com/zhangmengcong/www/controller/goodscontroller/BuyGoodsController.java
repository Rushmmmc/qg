package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.po.PageBean;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.zhangmengcong.www.constant.GoodsConstant.*;
import static com.zhangmengcong.www.constant.IndentConstant.DONOT_NEED_SO_MUCH_INTEGRAL;
import static com.zhangmengcong.www.constant.IndentConstant.INTEGRAL_NOT_ENOUGN;
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
        String username = (String)session.getAttribute("username");
        String seller = (String)request.getParameter("tempSeller");
        String goodsName = (String)request.getParameter("tempGoodsName");
        String goodsType = (String) request.getParameter("goodsType");
        int tempPrice = Integer.parseInt(request.getParameter("tempPrice"));
        boolean ifSuccess = false;
        //获取用户希望使用的积分量
        int integral = Integer.parseInt(request.getParameter("integral"));
        if(integral > tempPrice){
            request.setAttribute("message",DONOT_NEED_SO_MUCH_INTEGRAL);
        }
        //查询已有积分 若足够 扣除 不够则提示用户并不生成订单
        else if(factory.getIntegralService().useIntegralService(integral,username)){
            ifSuccess = true;
            indent.setAmount(Integer.parseInt(request.getParameter("tempAmount")));
            request.setAttribute("message", BUY_GOODS_SUCCESS);
            //单价
            indent.setGoodsType(goodsType);
            indent.setUseIntegral(integral);
            indent.setBuyer(username);
            indent.setPrice(tempPrice);
            indent.setGoodsName(goodsName);
            indent.setSeller(seller);
            factory.getBuyGoodsService().sellGoodsService(indent,0);
        }else {
            request.setAttribute("message",INTEGRAL_NOT_ENOUGN);
        }
        try {
            if(ifSuccess) {
                request.getRequestDispatcher("/DividePageController").forward(request, response);
            }else {
                //把信息储存回去再跳转
                request.setAttribute("tempSeller",seller);
                request.setAttribute("goodsType",goodsType);
                request.setAttribute("tempGoodsName",goodsName);
                request.setAttribute("tempPrice",tempPrice);
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
