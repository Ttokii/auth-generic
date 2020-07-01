package com.tokii.common.authgeneric.utils;

import com.alibaba.fastjson.JSON;
import com.tokii.common.authgeneric.constants.HttpCode;
import com.tokii.common.authgeneric.exception.ErrorInfo;
import com.tokii.common.authgeneric.response.BaseResponse;
import com.tokii.common.authgeneric.response.PlainResponse;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletResponse;
import java.io.PrintWriter;

@Slf4j
public class ResponseUtils {
    private static final String MSG_SUCCESS = "success";
    public static BaseResponse buildOk(String requestId) {
        BaseResponse response = new BaseResponse();
        response.setRequestId(requestId);
        response.setCode(HttpCode.CODE_200);
        response.setMessage(MSG_SUCCESS);
        return response;
    }

    public static <T> PlainResponse<T> buildOkWithData(String requestId, T data) {
        PlainResponse<T> response = new PlainResponse<>();
        response.setRequestId(requestId);
        response.setCode(HttpCode.CODE_200);
        response.setMessage(MSG_SUCCESS);
        response.setData(data);
        return response;
    }

    public static BaseResponse buildError(String requestId, Integer code, String msg) {
        BaseResponse response = new BaseResponse();
        response.setRequestId(requestId);
        response.setCode(code);
        response.setMessage(msg);
        return response;
    }

    public static BaseResponse buildError(String requestId, ErrorInfo errorInfo) {
        BaseResponse response = new BaseResponse();
        response.setRequestId(requestId);
        response.setCode(errorInfo.code);
        response.setSummary(errorInfo.summary);
        response.setMessage(errorInfo.desc);
        return response;
    }

    public static void out(ServletResponse response, BaseResponse data) {
        PrintWriter out = null;
        try {
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            out = response.getWriter();
            out.println(JSON.toJSONString(data));
        } catch (Exception e) {
            log.error(e + "输出JSON出错");
        } finally {
            if (out != null) {
                out.flush();
                out.close();
            }
        }
    }


}
