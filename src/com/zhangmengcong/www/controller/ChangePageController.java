package com.zhangmengcong.www.controller;

import com.zhangmengcong.www.controller.BaseServlet;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

import static com.zhangmengcong.www.constant.IndentConstant.IF_SELLER;
import static com.zhangmengcong.www.constant.IndentConstant.SHOPPING_CAR_FUNCTION;
import static com.zhangmengcong.www.constant.PageConstant.*;
import static com.zhangmengcong.www.constant.UserConstant.*;

/**
 * @author:zmc
 * @function: 用于页面跳转 传递信息
 * @date: 2020/4/29 21:11
 */
@WebServlet("/ChangePageController/*")
public class ChangePageController extends BaseServlet {
    Factory factory = new Factory();

    /**
     * 用户修改信息页面
     */
    public void changeMessagePage(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        request.setAttribute("emps", factory.getSelectPersonalMessageService().selectPersonalMessage(username));
        try {
            request.getRequestDispatcher("/change.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 管理员审核、删除商品 封禁/解封商品
     */
    public void adminManageGoodsAndSellerSystem(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            request.setAttribute("goodsList", factory.getGoodsPrintService().goodsPrintService());
            request.getRequestDispatcher("/manageGoods.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 卖家申报商品
     */
    public void sellerCommitGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
            response.setCharacterEncoding("UTF-8");
            String username = (String) request.getSession().getAttribute("username");
            if (factory.getCheckIfUserBeBanedService().checkIfUserBeBanedService(username)) {
                response.getWriter().write(YOU_HAVE_BEEN_BAN);
            }
    }

    /**
     *  去往卖家管理商品页面
     */
    public void goCheckSalesIndent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //选择打印卖家订单功能
        String username = (String) request.getSession().getAttribute("username");
        request.setAttribute("emps", factory.getPrintIndentService().printIndentService(0, username, IF_SELLER, 0));
        try {
            request.getRequestDispatcher("/manageIndent.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    /**
     * 去往用户(买家)管理个人商品页面
     */
    public void goCheckBuyIndent(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = (String) request.getSession().getAttribute("username");
        //选择打印买家订单功能
        request.setAttribute("emps", factory.getPrintIndentService().printIndentService(0, username, BUYER_FUNCTION, 0));
        try {
            request.getRequestDispatcher("/manageBuyerPersonalIndent.jsp").forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    /**
     * 买家去往商品购买页面
     */
    public void buyerSetBuyAmount(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //传递参数 否则完蛋
        //检测是否该用户自己的商品
        int id = Integer.parseInt(request.getParameter("id"));
        String username = (String)request.getSession().getAttribute("username");
        String message = factory.getCheckUserIfTheGoodsSellerService().checkUserIfTheGoodsSellerService(username,id);
        response.getWriter().write(message);
        request.getSession().setAttribute("goods", factory.getGetPriceAndGoodsNameService().getPriceAndGoodsNameService(id));
    }
    /**
     * 商家或用户去往对应订单的留言板
     */
    public void goToMessageBoard(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = (String) request.getSession().getAttribute("username");
        int ifSeller = Integer.parseInt(request.getParameter("ifSeller"));
        int id = Integer.parseInt(request.getParameter("id"));
        request.getSession().setAttribute("indentId", id);
        request.getSession().setAttribute("emps", factory.getPrintIndentService().printIndentService(id, username, ifSeller, 0));
        request.setAttribute("emps2", factory.getPrintMessageService().printMessageService(id));
        try {
            if (ifSeller == IF_SELLER) {
                request.getRequestDispatcher("/sellerCheckMessage.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/buyerCheckMessage.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    /**
     * 管理员去往审核申诉页面
     */
    public void changePageToHelpUser (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int level = (int)session.getAttribute("level");
        String username = (String) session.getAttribute("username");

        //根据用户等级打印申诉信息
        request.setAttribute("emps",factory.getPrintIndentService().printIndentService(0,username,BUYER_FUNCTION,0));
        request.setAttribute("appealList",factory.getPrintAppealService().printAppealServiceImpl(level,username));
        try {
            //为管理员 去往管理员处理审核页面
            if(level == ADMIN_LEVEL) {
                request.getRequestDispatcher("/helpUser.jsp").forward(request, response);
            }else {
                //为用户 去往用户个人申诉页面
                request.getRequestDispatcher("/helpMe.jsp").forward(request,response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    /**
     * 去往购物车
     */
    public void changePageToShoppingCar (HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession();
        String username = (String)session.getAttribute("username");
        Factory factory = new Factory();
        //选择打印买家订单功能
        request.setAttribute("emps",factory.getPrintIndentService().printIndentService
                (0,username,BUYER_FUNCTION,SHOPPING_CAR_FUNCTION));
            try {
                request.getRequestDispatcher("/shoppingCar.jsp").forward(request,response);
            }
        catch (ServletException e) {
            e.printStackTrace();
        }
    }
    /**
     * 注销
     */
    public void quit (HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //实现注销功能
        if (session.getAttribute(USER_NAME) != null) {
            //session中name不存在,即session已经被销毁
            Cookie cookie = new Cookie("username","destroy");
            cookie.setMaxAge(60);
            response.addCookie(cookie);
            session.invalidate();
            //删除session即可
            try {
                try {
                    request.getRequestDispatcher("/login.jsp").forward(request,response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (ServletException e) {
                e.printStackTrace();
            }
        }
        //实现注销功能
    }
    public void changePageToSellerManageGoodsPage (HttpServletRequest request, HttpServletResponse response) throws IOException{
        String sellerName = (String)request.getSession().getAttribute("username");
        List<Goods>goodsList =  factory.getGoodsPrintService().sellerPersonalGoodsPrintService(sellerName);
        request.setAttribute("goodsList",goodsList);
        try {
            request.getRequestDispatcher("/manageSellerPersonalGoods.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    public void changePageToUserService (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String goodsName = request.getParameter("goodsName");
        request.setAttribute("goodsName",goodsName);
        try {
            request.getRequestDispatcher("/userService.jsp").forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
}
