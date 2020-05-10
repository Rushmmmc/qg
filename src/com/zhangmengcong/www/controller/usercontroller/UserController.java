package com.zhangmengcong.www.controller.usercontroller;

import com.zhangmengcong.www.controller.BaseServlet;
import com.zhangmengcong.www.po.User;
import com.zhangmengcong.www.util.Factory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

import static com.zhangmengcong.www.constant.UserConstant.*;

/**
 * @author:zmc
 * @function: 用于用户修改信息
 * @date 2020/4/29 20:31
 */
@WebServlet("/UserController/*")
public class UserController extends BaseServlet {
    Factory factory = new Factory();
    /**
     * 修改个人信息
     */
    public void changeMessageController(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //实现修改个人信息功能
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //新建工厂对象
        Factory factory = new Factory();
        User user = new User();
        HttpSession session = request.getSession();

        //从前端接收用户的新信息 存入user对象
        String username = (String)session.getAttribute("username");
        String newUsername = request.getParameter("newusername");
        user.setPassword(request.getParameter("newpassword"));
        user.setUsername(newUsername);
        user.setMailAddress(request.getParameter("newaddress"));

        //调用修改方法 检验数据格式 返回提示信息
        String message = factory.getChangeMessageService().getChangeMessageServiceImpl(user,username);
        response.getWriter().write(message);

        //修改成功之后 修改cookie和session否则显示的用户信息会出错
        if (CHANGE_SUCCESS.equals(message)) {
            session.setAttribute("username", newUsername);
            Cookie cookie = new Cookie("username", newUsername);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
        }
    }
    /**
     * 找回密码功能
     */
    public void forgetPassword (HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String address = request.getParameter("address");
        Factory factory = new Factory();
        //在service检测是否存在该用户 发送邮件 并返回提示信息
        String message = factory.getForgetPasswordService().sendMail(address);
        response.getWriter().write(message);
    }
    /**
     * 登录功能
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码防止乱码
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("UTF-8");
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
                    //存储cookie存在的信息
                    ifCookieExist = true;
                    //获取cookie中存储的用户名
                    username = cookie.getValue();
                }
            }
        }
        //获取cookie
        //获取登录方式 有普通登录和cookie登录两种
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
            if (ifCookieExist) {
                response.getWriter().write(LOGIN_SUCCESS);
            } else {
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
    /**
     * 注册功能
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws IOException{
        //设置编码防止乱码
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        //设置编码防止乱码
        //获取区域 从前端获取各种信息
        String mailAddress = request.getParameter("mailaddress");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String captcha = request.getParameter("captcha");
        HttpSession session = request.getSession();
        String captchar = (String) session.getAttribute("captcha");
        //实现注册功能
        //获得注册服务返回的信息 service层进行数据判空 判断是否包含中文与特殊符号
        String message = factory.getRegisterAndLogin().register(username,password,mailAddress,captchar,captcha);
        response.getWriter().write(message);
        //实现注册功能
    }

}
