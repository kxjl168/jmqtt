package com.ztgm.base.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.util.StringUtils;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerViewResolver;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Application Lifecycle Listener implementation class FreemarkerFilter
 */
@PropertySource("classpath:project.properties")
public class JspFilter implements Filter {

    private Locale locale;

    private ApplicationContext ctx;

    @Value("${context-path}")
    private String contextpath;

    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String localeStr = filterConfig.getInitParameter("locale");
        if (StringUtils.hasText(localeStr)) {
            locale = new Locale(localeStr);
        } else {
            locale = Locale.getDefault();
        }
        

		// autowire起作用
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, filterConfig.getServletContext());
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (ctx == null) {
            ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(req.getSession().getServletContext());
            if (null == ctx) {
                throw new ExceptionInInitializerError("spring context is not loaded!");
            }
        }
        try {
            String name = req.getRequestURI();
           // name = name.substring(1, name.lastIndexOf(".ftl"));
            
            //去掉前缀
            name=name.replace(contextpath,"");
            
            InternalResourceViewResolver viewResolver = ctx.getBean(InternalResourceViewResolver.class);
            viewResolver.setPrefix("/templates");
            viewResolver.setViewNames("*.jsp");
            viewResolver.setOrder(2);
            viewResolver.setSuffix("");
            
            View view = viewResolver.resolveViewName(name, locale);
            if(view!=null)
            	view.render(null, req, res);
            
            
//            @SuppressWarnings("unchecked")
//            Map<String, Object> model = (Map<String, Object>) request.getAttribute(ViewRendererServlet.MODEL_ATTRIBUTE);
            
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    @Override
    public void destroy() {

    }

}
