package com.zhangmengcong.www.controller;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import static com.zhangmengcong.www.constant.CaptchaConstant.CAPTCHA_CHARACTER_AMOUNT;
import static com.zhangmengcong.www.constant.CaptchaConstant.LINE_AMOUNT;

/**
 * @author zmc
 * @function: 生成验证码 传递验证码字符
 * @date 2020/4/10 12:34
 */
@WebServlet("/captcha")
public class CaptchaController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {



        //实现丑丑验证码
        int width = 300;
        int height = 75;
        //创建一对象,在内存中代表一图片

        BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
        //三原色
        //美化图片
        //填充背景色
        Graphics g = image.getGraphics();
        //画笔对象
        g.setColor(Color.black);
        g.fillRect(0,0,width,height);
        //画边框
        g.setColor(Color.orange);
        g.drawRect(0,0,width-1,height-1);

        String str = "abcdefghijklmnopqrstuvwxyz0123456789";
        //生成随机角标
        Random ran = new Random();
        char[] cp = new char[4];
        for(int i=1; i<= CAPTCHA_CHARACTER_AMOUNT;i++) {
            int index = ran.nextInt(str.length());
            //获取字符
            char ch = str.charAt(index);
            //随机字符
            cp[i-1] = ch;
            //写验证码
            g.setFont(new Font(ch+"",10,20));
            g.drawString(ch+"", width/5*i, height/2);

        }
        //画干扰线
        g.setColor(Color.blue);
        //随机生成坐标点
        for(int j = 0;j<= LINE_AMOUNT;j++){
            int x1 = ran.nextInt(width);
            int x2 = ran.nextInt(width);
            int y1 = ran.nextInt(width);
            int y2 = ran.nextInt(width);
            g.drawLine(x1,x2,y1,y2);
        }
        //此处必须在传递图片之前完成 我debug了好久！！！
        HttpSession session = request.getSession();
        String abc = String.valueOf(cp);
        session.setAttribute("captcha",abc);
        //此处必须在传递图片之前完成 我debug了好久！！！

        //将图片展示到页面去
        ImageIO.write(image,"jpg",response.getOutputStream());
        //实现丑丑验证码

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws  IOException {
    this.doPost(request,response);
    }
}
