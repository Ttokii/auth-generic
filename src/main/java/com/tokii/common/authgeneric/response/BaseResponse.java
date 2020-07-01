package com.tokii.common.authgeneric.response;

import lombok.Data;

import java.io.Serializable;

@Data
public class BaseResponse implements Serializable {
    private String requestId;
    private Integer code;
    private String summary;
    private String message;
}
