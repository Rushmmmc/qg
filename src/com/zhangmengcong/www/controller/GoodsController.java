package com.zhangmengcong.www.controller;

import com.zhangmengcong.www.controller.BaseServlet;
import com.zhangmengcong.www.po.Goods;
import com.zhangmengcong.www.po.Indent;
import com.zhangmengcong.www.util.Encode;
import com.zhangmengcong.www.util.Factory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;

import static com.zhangmengcong.www.constant.GoodsConstant.*;
import static com.zhangmengcong.www.constant.IndentConstant.*;
import static com.zhangmengcong.www.constant.UserConstant.*;

/**
 * @author:zmc
 * @function: 把商品添加到用户的购物车
 * @date: 2020/5/3 15:40
 */
@WebServlet("/GoodsController/*")
public class GoodsController extends BaseServlet {
    Factory factory = new Factory();
    /**
     * 把商品添加到购物车
     */
    public void addGoodsToShoppingCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        int id = Integer.parseInt(request.getParameter("id"));
        String username = (String) session.getAttribute("username");
        Indent indent = new Indent();
        indent.setBuyer(username);
        //将商品id暂存入订单id
        indent.setId(id);

        //验证信息并返回提示
        String message = factory.getBuyGoodsService().buyGoodsService(indent, IF_SHOPPINGCAR,username);
        response.getWriter().write(message);
    }
    /**
     * 直接购买商品
     */
    public void buyGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Factory factory = new Factory();
        Indent indent = new Indent();
        HttpSession session = request.getSession();
        //从session获取用户名及要购买商品的信息
        String username = (String) session.getAttribute("username");
        Goods goods = (Goods) session.getAttribute("goods");
        goods.setAmount(Integer.parseInt(request.getParameter("tempAmount")));

        //获取用户希望使用的积分量
        int integral = Integer.parseInt(request.getParameter("integral"));
        //如果用户输入积分量大于价格 提示信息

        //查询已有积分 若足够 扣除 不够则提示用户并不生成订单
            indent.setAmount(Integer.parseInt(request.getParameter("tempAmount")));
            //设置订单信息
            indent.setGoodsType(goods.getType());
            indent.setUseIntegral(integral);
            indent.setBuyer(username);
            indent.setPrice(goods.getPrice());
            indent.setGoodsName(goods.getGoodsName());
            indent.setSeller(goods.getSeller());

            //验证数据格式并返回提示信息
            String message = factory.getBuyGoodsService().buyGoodsService(indent, 0,username);
            response.getWriter().write(message);
    }

    /**
     * 购买购物车的商品
     */
    public void buyGoodsFromShoppingCar(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        String message;
        Indent indent = new Indent();

        int id = Integer.parseInt(request.getParameter("id"));
        float price = Float.parseFloat(request.getParameter("price"));
        int integral = Integer.parseInt(request.getParameter("integral"));

            //设置订单信息
            indent.setId(id);
            indent.setPrice(price);
            indent.setBuyer(username);
            indent.setAmount(Integer.parseInt(request.getParameter("amount")));
            indent.setUseIntegral(integral);
            //检验数据 返回提示信息
            message = factory.getBuyGoodsFromShoppingCarService().buyGoodsFromShoppingCar(indent);
            response.getWriter().write(message);
    }

    /**
     * 商家申报新商品
     */
    public void commitGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        Factory factory = new Factory();

        Goods goods = new Goods();
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");


        String goodsName = request.getParameter("goodsName");

        goods.setSeller(username);
        goods.setType(request.getParameter("type"));
        goods.setGoodsName(goodsName);
        goods.setImformation(request.getParameter("imformation"));
        goods.setPrice(Float.parseFloat(request.getParameter("price")));
        goods.setAmount(Integer.parseInt(request.getParameter("amount")));

        String message = factory.getAddGoodsService().addGoodsService(goods);
        //成功后给上传图片页面存储商品名
        if (ADD_GOODS_SUCCESS.equals(message)) {
            request.getSession().setAttribute("goodsName", goodsName);
        }
        response.getWriter().write(message);
    }

    /**
     * 管理员通过或删除商品
     */
    public void goodsDeleteOrPassController(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        //检测管理员希望审核还是删除商品
        int ifDelete = Integer.parseInt(request.getParameter("ifDelete"));
        int id = Integer.parseInt(request.getParameter("id"));
        Factory factory = new Factory();
        String username = (String) request.getSession().getAttribute("username");
        String message = factory.getDeleteOrPassGoodsService().deleteOrPassGoodsService(id, ifDelete,username);
        response.getWriter().write(message);
    }

    /**
     * 商家自主设置在首页用户推荐内容
     */
    public void recommend(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        //获取推荐内容
        String recommend = request.getParameter("recommend");
        int indentId = Integer.parseInt(request.getParameter("indentId"));

        String message = factory.getGoodsRecommendService().chooseGoodsRecommendService(recommend, indentId);
        response.getWriter().write(message);
    }

    /**
     * 用户申请退货
     */
    public void returnGoods(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String type = "";
        //接收订单号 处理方式 ifSeller = 1 即为商家处理退货
        int ifSeller = Integer.parseInt(request.getParameter("ifSeller"));
        int indentId = Integer.parseInt(request.getParameter("indentId"));
        //如果是商家处理退货
        if (ifSeller == IF_SELLER) {
            type = request.getParameter("type");
        }
        //否则为用户申请退货
        //传入参数 获取提示信息
        String message = factory.getUserApplySalesReturnService()
                .returnGoodsService(ifSeller, indentId, type);
        response.getWriter().write(message);
    }
    /**
     * 在广告页智能推送商品信息
     */
    public void seleteGoodsByInterest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List<Goods> list = factory.getSelectGoodsByInterest().selectGoodsByInterest(username);

        request.setAttribute("goodsList", list);
        try {
            //如果是游客
            if ((int) (session.getAttribute(LEVEL)) == VISITOR_LEVEL) {
                request.getRequestDispatcher("/visitorAd.jsp").forward(request, response);
            }
            //如果是管理员
            else if ((int) (session.getAttribute(LEVEL)) == ADMIN_LEVEL) {
                request.getRequestDispatcher("/DividePageController").forward(request, response);
            }
            //否则是普通用户
            else {
                request.getRequestDispatcher("/ad.jsp").forward(request, response);
            }
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    /**
     * 商户上传商品图片
     */
    public void uploadPhoto (HttpServletRequest request, HttpServletResponse response) throws IOException{
        Factory factory = new Factory();
        response.setCharacterEncoding("UTF-8");

        //获得磁盘文件条目工厂
        DiskFileItemFactory factory2 = new DiskFileItemFactory();
        //获取文件需要上传到的路径
        String path = this.getServletContext().getRealPath("./" + "photo");
        File file = new File(path);

        if (!file.exists()) {
            //不存在则创建目录
            file.mkdirs();
        }
        factory2.setRepository(new File(path));
        //设置缓存的大小当上传文件的容量超过该缓存时直接放到暂时存储室
        factory2.setSizeThreshold(1024 * 1024);
        try {
            ServletFileUpload upload = new ServletFileUpload(factory2);
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {
                //获取路径名
                String value = item.getName();
                //索引到最后一个反斜杠
                int start = value.lastIndexOf("\\");
                //截取 上传文件的 字符串名字，加1是 去掉反斜杠，
                String filename = value.substring(start + 1);
                //文件名加上用户名 防止图片名重复导致出错

                String goodsName = (String)request.getSession().getAttribute("goodsName");
                filename =  goodsName+ filename;
                if(!filename.contains(".jpg") && !filename.contains(".bmp") && !filename.contains(".png")){
                    response.getWriter().write("图片格式仅支持jpg、bmp、png！");
                    continue;
                }
                OutputStream out = new FileOutputStream(new File(path, filename));
                InputStream in = item.getInputStream();
                int length;
                byte[] buf = new byte[1024];
                // in.read(buf) 每次读到的数据存放在 buf 数组中
                while ((length = in.read(buf)) != -1) {
                    //在 buf 数组中 取出数据 写到 （输出流）磁盘上
                    out.write(buf, 0, length);
                }
                in.close();
                out.close();

                String message = factory.getUpdateGoodsPhotoService().updateGoodsPhotoService(filename,goodsName);
                //如果上传图片失败 删除预商品
//                if(!ADD_PHOTO_SUCCESS.equals(message)){
//                    factory.getDeleteOrPassGoodsService().deleteGoodsService(goodsName);
//                }
                response.getWriter().write(message);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void checkUserIfExistGoods (HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        String username = (String)request.getSession().getAttribute("username");
        boolean ifGoodsExist = factory.getCheckIfUserExistGoodsService().checkIfUserExistGoodsService(username);
        if(!ifGoodsExist){
            response.getWriter().write("您暂无商品，快去申请卖出您的宝贝吧( •̀ ω •́ )y");
        }
    }
    public void SellerDeleteGoods (HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        int goodsId = Integer.parseInt(request.getParameter("goodsId"));
        String username = (String)request.getSession().getAttribute("username");
        String message = factory.getDeleteOrPassGoodsService().sellerDeleteGoods(goodsId,username);
        response.getWriter().write(message);
    }
    public void changeGoodsMessage (HttpServletRequest request, HttpServletResponse response) throws IOException{
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");

        Goods goods = new Goods();
        goods.setId(Integer.parseInt(request.getParameter("goodsId")));
        goods.setAmount(Integer.parseInt(request.getParameter("amount")));
        goods.setPrice(Float.parseFloat(request.getParameter("price")));
        goods.setGoodsName(request.getParameter("goodsName"));

        String message = factory.getChangeGoodsMessageService().changeGoodsMessageService(goods);
        response.getWriter().write(message);
    }
}