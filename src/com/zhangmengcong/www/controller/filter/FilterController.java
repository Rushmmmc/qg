package com.zhangmengcong.www.controller.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static com.zhangmengcong.www.constant.PageConstant.CAPTCHA;
import static com.zhangmengcong.www.constant.PageConstant.REGISTER_CONTROLLER;

/**
 * @author zmc
 * @function: 拦截不登录尝试进入系统的用户
 * @date: 2020/4/15 11:52
 */
@WebFilter("/*")
public class FilterController implements javax.servlet.Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        String uri = request.getRequestURI();
        String url=request.getRequestURI();
        if(url.indexOf(".css")>0||url.indexOf(".js")>0||url.indexOf(".png")>0) {
            chain.doFilter(request, resp);
            return;
        }
        if ( uri.contains(REGISTER_CONTROLLER) || uri.contains(CAPTCHA)
    || uri.contains("/style.css") || uri.contains("register.jsp") || uri.contains("Register") || uri.contains("/3.jpg") || uri.contains("/LoginController.jsp")
        || uri.contains("/page") || uri.contains("/login.jsp") || uri.contains("/login") ||uri.contains("/forget.jsp") ||
        uri.contains("/UserController/forgetPassword")){
            chain.doFilter(req, resp);
        }else {
            Object userName = request.getSession().getAttribute("username");
            if(userName != null){
                chain.doFilter(req, resp);
            }else{
                request.getRequestDispatcher("/login.jsp").forward(request,resp);
            }
        }
    }

    @Override
    public void init(FilterConfig config) {
    }

}
