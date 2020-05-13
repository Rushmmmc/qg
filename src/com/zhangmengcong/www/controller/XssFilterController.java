package com.zhangmengcong.www.controller;

import com.zhangmengcong.www.service.service.xssservice.XssHttpServletRequestWrapperService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author:zmc
 * @function: Xss防御 防止Xss攻击
 * @date: 2020/4/23 16:17
 */
@WebFilter("/*")
public class XssFilterController implements Filter {
    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        XssHttpServletRequestWrapperService xssRequest = new XssHttpServletRequestWrapperService((HttpServletRequest)req);
        chain.doFilter(xssRequest,resp);
    }

    @Override
    public void init(FilterConfig config) {

    }

}
