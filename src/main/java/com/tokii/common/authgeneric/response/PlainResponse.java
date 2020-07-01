package com.tokii.common.authgeneric.response;

import lombok.Data;

@Data
public class PlainResponse<T> extends BaseResponse {
    private T data;

}
