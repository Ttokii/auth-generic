package com.tokii.common.authgeneric.config;

import com.alibaba.fastjson.JSON;
import com.tokii.common.authgeneric.constants.AuthConstants;
import com.tokii.common.authgeneric.utils.AuthCommonUtils;
import com.tokii.common.authgeneric.utils.ResponseUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 认证成功
 */
@Component
public class AuthSuccessHandler implements AuthenticationSuccessHandler {

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        String requestId = AuthCommonUtils.getToString(request.getParameter(AuthConstants.PARAM_REQUEST_ID));
        PrintWriter out = response.getWriter();
        out.write(JSON.toJSONString(ResponseUtils.buildOk(requestId)));
        out.flush();
        out.close();
    }
}
