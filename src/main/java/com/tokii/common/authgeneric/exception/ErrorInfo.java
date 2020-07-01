package com.tokii.common.authgeneric.exception;

import com.tokii.common.authgeneric.constants.HttpCode;

public enum ErrorInfo {
    INVALID_AUTH_TYPE(HttpCode.CODE_400, "InvalidParam.authType", "the authType does not exist in our config!"),
    INVALID_PARAM(HttpCode.CODE_400, "InvalidParam.authType", "Specific authType does not exist in our config!"),
    INVALID_ACCOUNT_NAME(HttpCode.CODE_401, "InvalidParam.AccountName", "Specific account name does not exist in our records!"),
    INVALID_PASSWORD(HttpCode.CODE_401, "InvalidParam.Password", "Specific password is invalid!"),
    AUTH_FAIL(HttpCode.CODE_401, "authFail", "username or password is invalid!"),
    TOKEN_EXPIRED(HttpCode.CODE_401, "TokenExpired", "token is expired!");

    public Integer code;
    public String summary;
    public String desc;
    private ErrorInfo(Integer code, String summary, String desc) {
        this.code = code;
        this.summary = summary;
        this.desc = desc;
    }
    @Override
    public String toString() {
        return "{'code': "+code+", 'summary': '"+summary+"', 'desc': '"+desc+"'}";
    }
}
