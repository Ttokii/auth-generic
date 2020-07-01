package com.tokii.common.authgeneric.filter;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AddResponseHeaderFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
//        httpServletResponse.addHeader("X-Frame-Options", "DENY");
//        httpServletResponse.addHeader("Cache-Control", "no-cache, no-store, must-revalidate, max-age=0");
//        httpServletResponse.addHeader("Cache-Control", "no-cache='set-cookie'");
//        httpServletResponse.addHeader("Pragma", "no-cache");
        httpServletResponse.setContentType("application/json;charset=utf-8");
        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }
}
