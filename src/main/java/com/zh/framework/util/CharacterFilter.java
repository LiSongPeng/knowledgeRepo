package com.zh.framework.util;

import javax.servlet.*;
import java.io.IOException;

/**
 * created by lihuibo on 17-9-8 下午2:38
 */
public class CharacterFilter implements Filter {
    private String encoding;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        chain.doFilter(request,response);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        encoding = filterConfig.getInitParameter("encoding");
    }
}
