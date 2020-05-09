package com.zhangmengcong.www.controller.goodscontroller;

import com.zhangmengcong.www.util.Factory;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

import static com.zhangmengcong.www.constant.GoodsConstant.ADD_PHOTO_SUCCESS;

/**
 * @author:zmc
 * @function:
 * @date: 2020/5/8 11:11
 */
@WebServlet("/uploadPhoto")
public class UploadPhoto extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        //商户上传商品简介图片
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
        //设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
        factory2.setSizeThreshold(1024 * 1024);
        //高水平的API文件上传处理
        try {
            ServletFileUpload upload = new ServletFileUpload(factory2);
            //可以上传多个文件
            List<FileItem> list = upload.parseRequest(request);
            for (FileItem item : list) {

                    //对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
                    //获取路径名
                    String value = item.getName();
                    //索引到最后一个反斜杠
                    int start = value.lastIndexOf("\\");
                    //截取 上传文件的 字符串名字，加1是 去掉反斜杠，
                    String filename = value.substring(start + 1);
                    //文件名加上用户名 防止图片名重复导致出错
                    if(!filename.contains(".jpg") && !filename.contains(".bmp") && !filename.contains(".png")){
                        response.getWriter().write("图片格式仅支持jpg、bmp、png！");
                        continue;
                    }
                    String goodsName = (String)request.getSession().getAttribute("goodsName");
                    filename =  goodsName+ filename;
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
                    Factory factory = new Factory();
                    String message = factory.getUpdateGoodsPhotoService().updateGoodsPhotoService(filename,goodsName);
                    //如果上传图片失败 删除预商品
                    if(!ADD_PHOTO_SUCCESS.equals(message)){
                        factory.getDeleteOrPassGoodsService().deleteGoodsService(goodsName);
                    }
                    response.getWriter().write(message);
                }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }
}
