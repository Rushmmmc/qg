package com.zhangmengcong.www.controller;

import com.zhangmengcong.www.po.DividePage;
import com.zhangmengcong.www.po.DividePageParameter;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;



/**
 * @author:zmc
 * @function: 用于分页 传递信息
 * @date: 2020/4/29 16:19
 */
@WebServlet("/DividePageController")
public class DividePageController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        Factory factory = new Factory();
        HttpSession session = request.getSession();

        Goods goods = new Goods();
        goods.setGoodsName(request.getParameter("goodsName"));
        goods.setType(request.getParameter("type"));
        goods.setSeller(request.getParameter("seller"));
        DividePageParameter dividePageParameter = new DividePageParameter();
        //当前页码
        dividePageParameter.setCurrentPage(request.getParameter("currentPage"));
        //每页显示的条数
        dividePageParameter.setRows(request.getParameter("rows"));
        //用户输入的筛选价格的最大值和最小值
        String rangeMin = request.getParameter("rangemin");
        String rangeMax = request.getParameter("rangemax");
        String rank = request.getParameter("rank");
        dividePageParameter.setRank(rank);
        dividePageParameter.setRangeMin(rangeMin);
        dividePageParameter.setRangeMax(rangeMax);
        int level = (int) session.getAttribute("level");

        //获取用户是否需要价格正逆序排序价格服务
        dividePageParameter.setRank(request.getParameter("rank"));
        dividePageParameter.setGoods(goods);
        //将用户输入的信息回显回去
        DividePage dp = new DividePage();
        dp.setGoods(goods);
        dp.setMaxPrice(rangeMax);
        dp.setMinPrice(rangeMin);
        dp.setRank(rank);
        dp.setPb(factory.getPrintTableService().byPage(dividePageParameter));
        request.setAttribute("dp",dp);
        String address = factory.getGoToMainpageService().goToMainpageService(level);
        try {
            request.getRequestDispatcher(address).forward(request,response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
    this.doPost(request,response);
    }
}
