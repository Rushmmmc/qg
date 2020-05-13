package com.zhangmengcong.www.controller;

import com.zhangmengcong.www.controller.BaseServlet;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.zhangmengcong.www.constant.IndentConstant.*;

/**
 * @author:zmc
 * @function: 订单相关controller
 * @date: 2020/5/7 22:42
 */
@WebServlet("/IndentController/*")
public class IndentController extends BaseServlet {
    Factory factory = new Factory();

    /**
     * 用户给订单留下详细评价
     */
    public void addEvaluate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int indentId = Integer.parseInt(request.getParameter("indentId"));
        String evaluate = request.getParameter("evaluate");
        //调用方法 检验数据 并返回提示信息
        String message = factory.getUserEvaluateIndentService().userEvaluateIndent(evaluate,indentId);
        response.getWriter().write(message);
    }

    /**
     * 用户或商家删除订单
     */
    public void deleteIndent (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //判断是用户还是商家
        int ifSeller = Integer.parseInt(request.getParameter("ifSeller"));
        int indentId = Integer.parseInt(request.getParameter("indentId"));
        //调用方法 检验数据 并返回提示信息
        String message = factory.getDeleteIndentService().deleteIndent(ifSeller,indentId);
        response.getWriter().write(message);
    }
    /**
     * 删除购物车的订单
     */
    public void deleteShoppingCarIndent (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int indentId = Integer.parseInt(request.getParameter("indentId"));
        String message = factory.getDeleteShoppingCarIndentService().deleteShoppingCarIndent(indentId);
        response.getWriter().write(message);
    }

    /**
     * 删除留言板的消息
     */
    public void deleteMessage (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();

        int ifSeller = Integer.parseInt(request.getParameter("ifSeller"));
        int id = Integer.parseInt(request.getParameter("id"));
        int indentId = (int)session.getAttribute("indentId");
        factory.getDeleteMessageService().deleteMessageServiceImpl(id,indentId,ifSeller);
        try {
            if(ifSeller != IF_SELLER) {
                request.getRequestDispatcher("/ChangePageController/goToMessageBoard?ifSeller=0&id=" + indentId).forward(request, response);
            }else {
                request.getRequestDispatcher("/ChangePageController/goToMessageBoard?ifSeller=1&id=" + indentId).forward(request,response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 商家生成订单xls文件
     */
    public void generateXlsIndent (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        //获取路径
        String path = this.getServletContext().getRealPath("./");
        String username = (String)session.getAttribute("username");
        factory.getGenerateFileService().geneateFileService(username,path);
        path = path + "data\\";
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs(); //创建目录
        }


        //得到要下载的文件名
        String fileName = username + ".xls";
        //设置文件名的编码,将不安全的文件名改为UTF-8
        fileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8);
        FileInputStream fis = new FileInputStream(path+fileName);
        //创建字节输出流
        ServletOutputStream sos = response.getOutputStream();
        //告知客户端要下载文件
        response.setHeader("content-disposition","attachment;filename="+fileName);
        response.setHeader("content-type","application/vnd.ms-excel");
        //执行输出操作
        int len;
        byte[] buff = new byte[1024];
        while(( len=fis.read(buff))!=-1){
            sos.write(buff,0,len);
        }
        //关闭资源
        sos.close();
        fis.close();
    }
    /**
     * 商家未发货 用户放弃订单
     */
    public void giveUpIndent (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");


        String message = factory.getGiveUpIndentService().giveUpIndent(
                Integer.parseInt(request.getParameter("indentId")));
        response.getWriter().write(message);
    }
    /**
     * 卖家发货
     */
    public void sellerSendGoods (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        int indentId = Integer.parseInt(request.getParameter("indentId"));
        String message = factory.getSellerSendGoodsService().sellerSendGoods(indentId);
        response.getWriter().write(message);
    }
    /**
     * 卖家修改订单信息
     */
    public void changeIndent (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        Indent indent = new Indent();
        indent.setId(Integer.parseInt(request.getParameter("indentId")));
        indent.setAmount(Integer.parseInt(request.getParameter("amount")));
        indent.setGoodsName(request.getParameter("goodsName"));
        indent.setPrice(Float.parseFloat(request.getParameter("price")));
        String message = factory.getSellerChangeIndentService().sellerChangeIndent(indent);
        response.getWriter().write(message);
    }
    /**
     * 用户确认收货
     */
    public void finishIndent (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String username = (String)request.getSession().getAttribute("username");

        int indentId = Integer.parseInt(request.getParameter("indentId"));
        String message = factory.getFinishIndentService().finishIndent(indentId,username);
        response.getWriter().write(message);
    }
    /**
     * 用户给好评
     */
    public void giveGoodReputation (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String message = factory.getGiveGoodsReputationService().giveGoodsReputation(id);
        response.getWriter().write(message);
    }
    /**
     * 用户给差评
     */
    public void giveBadReputation (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int id = Integer.parseInt(request.getParameter("id"));
        String message = factory.getGiveGoodsReputationService().giveBadReputation(id);
        response.getWriter().write(message);
    }
    /**
     * 商家拒绝售卖
     */
    public void rejectIndent (HttpServletRequest request, HttpServletResponse response) throws IOException{
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        int indentId = Integer.parseInt(request.getParameter("indentId"));
        String message = factory.getRejectSellGoodsService().rejectSellGoods(indentId);
        response.getWriter().write(message);
    }

}
