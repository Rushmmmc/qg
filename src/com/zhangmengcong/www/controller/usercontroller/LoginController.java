package com.zhangmengcong.www.controller.usercontroller;


import com.zhangmengcong.www.util.Factory;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static com.zhangmengcong.www.constant.UserConstant.*;

/**
 * @author:zmc
 * @function: 用于用户登录
 * @date:2020/4/29 9:22
 */
@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
//设置编码防止乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");
        //设置编码防止乱码
        //获取区域 从前端获取各种信息
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String captcha = request.getParameter("captcha");
        //获取区域

        if(password == null || username == null || captcha == null){
            password = username = captcha = "";
            //防止用户使用cookie登录时发生NPE
        }
        int level;
        //创建工厂对象
        Factory factory = new Factory();
        //获取session
        HttpSession session = request.getSession();

        //从另一个Servlet中接受验证码图片中的字符
        String captchar = (String) session.getAttribute("captcha");
        //获取区域
        //获取cookie
        boolean ifCookieExist =false;
        Cookie[] cookies = request.getCookies();
        if(cookies != null){
            for(Cookie cookie :cookies){
                if(USER_NAME.equals(cookie.getName()) && !cookie.getValue().contains("destroy")){
                    ifCookieExist = true;
                    username = cookie.getValue();
                }
            }
        }
        //获取cookie
        //实现登录功能
            String way = request.getParameter("way");

            //如果用户不使用cookie并想登录其他账号
            if(NORMAL.equals(way) && !request.getParameter(USER_NAME).equals(username)){
                ifCookieExist = false;
                username = request.getParameter("username");
            }
            //在service进行判空 判断是否包含中文以及特殊符号 并返回提示信息
            String message = factory.getRegisterAndLogin().login(username, password, captcha, captchar);
            if ( (message.contains(LOGIN_SUCCESS)) || ifCookieExist) {
                //用户信息正确并且验证码输入正确 或存在cookie 才能允许登陆
                Cookie cookie = new Cookie("username", username);
                cookie.setMaxAge(60 * 60);
                response.addCookie(cookie);
                level = factory.getLevelService().getLevelService(username);
                //调用检索等级方法,获取用户的等级
                session.setAttribute("username", username);
                //登陆成功,在session储存用户名
                session.setAttribute("level", level);
                session.setAttribute("sendLevel", factory.getEstimateStatus().estimateStatus(level));
                //cookie登录另外提示
                if(ifCookieExist){
                    response.getWriter().write(LOGIN_SUCCESS);
                }else {
                    response.getWriter().write(message);
                }
            }else {
                //登录失败 分为两种方式
                //使用cookie登录 并且不存在cookie
                if (COOKIE.equals(way)) {
                        response.getWriter().write("您暂时不存在cookie┭┮﹏┭┮");
                }else {
                    //普通登录 用户名与密码不匹配
                    response.getWriter().write(message);
                }
                }
        }
        //实现登录功能

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }


}