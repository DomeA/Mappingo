package com.domeastudio.mappingo.application.Util.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import java.io.IOException;

/**
 * Servlet Filter implementation class userFilter
 */
public class SessionFilter implements Filter {

    private FilterConfig filterConfig;

    /**
     * Default constructor.
     */
    public SessionFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
        this.filterConfig = null;
    }

    public boolean isContains(String container, String[] regx) {
        boolean result = false;
        for (int i = 0; i < regx.length; i++) {
            if (container.contains(regx[i])) {
                return true;
            }
        }
        return result;
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // TODO Auto-generated method stub
        // place your code here
        HttpServletRequest hrequest = (HttpServletRequest) request;
        HttpServletResponseWrapper wrapper = new HttpServletResponseWrapper((HttpServletResponse) response);

        String excludeStrings = filterConfig.getInitParameter("excludeStrings");        // 登录登陆页面
        String includeStrings = filterConfig.getInitParameter("includeStrings");    // 过滤资源后缀参数
        String redirectPath = hrequest.getContextPath() + "/" + filterConfig.getInitParameter("redirectPath");// 没有登陆转向页面
        //String disabletestfilter = filterConfig.getInitParameter("disableFilter");// 过滤器是否有效

//        if (disabletestfilter.toUpperCase().equals("Y")) {    // 过滤无效
//            chain.doFilter(request, response);
//            return;
//        }
        String[] excludeList = excludeStrings.split(";");
        String[] includeList = includeStrings.split(";");
        Object userInfo = hrequest.getSession().getAttribute("userInfo");//判断用户是否登录
        if (!this.isContains(hrequest.getRequestURI(), includeList)) {// 只对指定过滤参数后缀进行过滤
            chain.doFilter(request, response);
            return;
        }

        if (this.isContains(hrequest.getRequestURI(), excludeList)) {// 对登录页面不进行过滤
            if(userInfo!=null){
                hrequest.getSession().removeAttribute("userInfo");
            }
            chain.doFilter(request, response);
            return;
        }

        if (userInfo == null) {
            wrapper.sendRedirect(redirectPath);
            return;
        } else {
            chain.doFilter(request, response);
            return;
        }
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
        filterConfig = fConfig;
    }

}
