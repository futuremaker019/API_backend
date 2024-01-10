package com.stock.analysis.config.jwt;

import javax.servlet.*;
import java.io.IOException;

public class JwtFilter implements Filter {
    public JwtFilter(TokenManager tokenManager) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

    }
}
