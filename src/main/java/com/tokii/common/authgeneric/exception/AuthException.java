package com.tokii.common.authgeneric.exception;

import lombok.Data;

@Data
public class AuthException extends Exception {
    private Integer code;
    private String msg;

    public AuthException(ErrorInfo errorInfo) {
        this.code = errorInfo.code;
        this.msg = errorInfo.desc;
    }
}
