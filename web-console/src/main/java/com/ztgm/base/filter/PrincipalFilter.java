package com.ztgm.base.filter;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import javax.servlet.*;
import java.io.IOException;
import java.util.Map;

public class PrincipalFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated()){
            Map principal = (Map) subject.getPrincipal();
            servletRequest.setAttribute("principal",principal);
        }

    }

    @Override
    public void destroy() {

    }
}
