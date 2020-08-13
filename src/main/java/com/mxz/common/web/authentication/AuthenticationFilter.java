package com.mxz.common.web.authentication;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description
 * @Date 2019/10/29 14:53
 * @Author mxz
 */
public class AuthenticationFilter implements Filter {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)servletRequest;
        HttpServletResponse response = (HttpServletResponse)servletResponse;
//        if () {
//
//        } else {
//            response.sendRedirect("/index33.html");
//        }
        filterChain.doFilter(servletRequest, servletResponse);
    }
}
