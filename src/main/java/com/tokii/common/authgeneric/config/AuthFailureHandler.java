package com.tokii.common.authgeneric.config;

import com.alibaba.fastjson.JSON;
import com.tokii.common.authgeneric.constants.AuthConstants;
import com.tokii.common.authgeneric.utils.AuthCommonUtils;
import com.tokii.common.authgeneric.utils.ResponseUtils;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证失败
 */
@Component
public class AuthFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        PrintWriter out = httpServletResponse.getWriter();
        String requestId = AuthCommonUtils.getToString(httpServletRequest.getParameter(AuthConstants.PARAM_REQUEST_ID));
        out.write(JSON.toJSONString(ResponseUtils.buildError(requestId, 401, e.getMessage())));
        out.flush();
        out.close();
    }
}
