package com.tokii.common.authgeneric.filter;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.CollectionUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Map;

@Slf4j
public class UserPwdAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static final ThreadLocal<Map<String, String>> requestParamsThreadLocalMap = new ThreadLocal<>();

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        return super.attemptAuthentication(request, response);
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        String password = getRequestParams(request).get(SPRING_SECURITY_FORM_PASSWORD_KEY);
        if(StringUtils.isNotBlank(password)){
            return password;
        }
        return super.obtainPassword(request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        String username = getRequestParams(request).get(SPRING_SECURITY_FORM_USERNAME_KEY);
        if(StringUtils.isNotBlank(username)){
            return username;
        }
        return super.obtainUsername(request);
    }

    private Map<String,String> getRequestParams(HttpServletRequest request){
        try {
            if(CollectionUtils.isEmpty(requestParamsThreadLocalMap.get())) {
                Map<String,String> requestParams = JSON.parseObject(request.getInputStream(), Map.class);
                requestParamsThreadLocalMap.set(requestParams);
                return requestParams;
            } else {
                return requestParamsThreadLocalMap.get();
            }
        } catch (IOException e) {
            return Collections.emptyMap();
        }
    }
}
