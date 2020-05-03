package com.zhangmengcong.www.controller.indentcontroller;

import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.zhangmengcong.www.constant.PageConstant.MANAGE_INDENT;

/**
 * @author:zmc
 * @function: 订单信息生成xls文件
 * @date: 2020/5/2 19:49
 */
@WebServlet("/GenerateXlsIndentController")
public class GenerateXlsIndentController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Factory factory = new Factory();
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        //获取路径
        String path = this.getServletContext().getRealPath("./");
        String username = (String)session.getAttribute("username");
        factory.getGeneateFileService().geneateFileService(username,path);
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
        File file1 = new File(path);
        boolean ifSuccess = file1.delete();
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



        try {
                request.getRequestDispatcher("/ChangePageController?method=" + MANAGE_INDENT).forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
