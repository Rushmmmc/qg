package com.zhangmengcong.www.controller.pagecontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;
import static com.zhangmengcong.www.constant.PageConstant.*;
import static com.zhangmengcong.www.constant.UserConstant.YOU_HAVE_BEEN_BAN;
/**
 * @author:zmc
 * @function: 用于页面跳转 传递信息
 * @date: 2020/4/29 21:11
 */
@WebServlet("/ChangePageController")
public class ChangePageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws  IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        Factory factory = new Factory();
        String username = (String) session.getAttribute("username");
        //根据method跳转不同页面并存储表格信息
        String method = request.getParameter("method");

        //前往个人中心
        if(CHANGE_MESSAGE.equals(method)) {
            request.setAttribute("emps", factory.getPrintTableService().selectPersonalMessage(username));
            try {
                request.getRequestDispatcher("/change.jsp").forward(request, response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        //前往管理员审核商品页面
        if(MANAGE_SYSTEM.equals(method)){
            request.setAttribute("goodsList",factory.getGoodsPrintService().goodsPrintService());
            try {
                request.getRequestDispatcher("/manageGoods.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        //前往商家提交新商品页面
        if(COMMIT.equals(method)){
            try {
                if(factory.getCheckIfUserBeBanedService().checkIfUserBeBanedService(username)){
                    request.setAttribute("message",YOU_HAVE_BEEN_BAN);
                    request.getRequestDispatcher("/DividePageController").forward(request,response);
                }else {
                    request.getRequestDispatcher("/commit.jsp").forward(request,response);
                }
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        //前往卖家管理订单页面
        if(MANAGE_INDENT.equals(method)){
            //选择打印卖家订单功能
           request.setAttribute("emps",factory.getPrintIndentService().printIndentService(username,IF_SELLER,0));
            try {
                request.getRequestDispatcher("/manageIndent.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        //前往买家管理订单页面
        if(MANAGE_BUYER_PERSONAL_INDENT.equals(method)){
            //选择打印买家订单功能
            request.setAttribute("emps",factory.getPrintIndentService().printIndentService(username,IF_BUYER,0));
            try {
                request.getRequestDispatcher("/manageBuyerPersonalIndent.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }


        //前往填写购买数量和积分抵现页面
        if(SET_AMOUNT.equals(method)){
            request.setAttribute("tempGoodsName",request.getParameter("tempGoodsName"));
            request.setAttribute("tempPrice", request.getParameter("tempPrice") );
            request.setAttribute("tempSeller", request.getParameter("tempSeller"));
            try {
                request.getRequestDispatcher("/setAmount.jsp").forward(request,response);
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }

        //前往留言板
        if(MESSAGE_BORAD.equals(method)){
            int ifSeller = Integer.parseInt(request.getParameter("ifSeller"));
            request.setAttribute("emps",factory.getPrintIndentService().printIndentService(username,ifSeller,0));
            try {
                if(ifSeller == IF_SELLER) {
                    request.getRequestDispatcher("/sellerCheckMessage.jsp").forward(request, response);
                }else {
                    request.getRequestDispatcher("/buyerCheckMessage.jsp").forward(request, response);
                }
                } catch (ServletException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    this.doPost(request,response);
    }
}
