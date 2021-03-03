package com.cxxy.demo.filter;


import com.cxxy.demo.util.StringUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class LoginFilter implements Filter {

    /**
     * 容器加载的时候
     * @param filterConfig
     * @throws ServletException
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("--------------------init LoginFilter--------------------");

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("--------------------doFilter LoginFilter--------------------");
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        String token = httpServletRequest.getHeader("token");
        if(StringUtil.isEmpty(token)){
            token = httpServletRequest.getParameter("token");
        }
        if(StringUtil.isEmpty(token)){
        }else {

        }

    }

    /**
     * 容器销毁的时候
     */
    @Override
    public void destroy() {
        System.out.println("--------------------destroy LoginFilter--------------------");

    }
}
