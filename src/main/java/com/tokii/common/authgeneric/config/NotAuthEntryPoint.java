package com.tokii.common.authgeneric.config;

import com.tokii.common.authgeneric.constants.HttpCode;
import com.tokii.common.authgeneric.exception.ErrorInfo;
import com.tokii.common.authgeneric.utils.ResponseUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Objects;

/**
 * <p>认证权限入口 - 未登录的情况下访问所有接口都会拦截到此</p>
 */
@Slf4j
@Component
public class NotAuthEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        if(Objects.isNull(e)) {
            ResponseUtils.out(httpServletResponse, ResponseUtils.buildError("", ErrorInfo.TOKEN_EXPIRED));
        } else {
            ResponseUtils.out(httpServletResponse, ResponseUtils.buildError("", HttpCode.CODE_401, e.getMessage()));
        }
    }
}
