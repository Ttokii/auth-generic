package com.tokii.common.authgeneric.utiltests;

import com.alibaba.fastjson.JSON;
import com.tokii.common.authgeneric.exception.ErrorInfo;
import org.junit.Test;

public class CommonTest {

    @Test
    public void test() {
        System.out.println(JSON.toJSONString(ErrorInfo.INVALID_PARAM.toString()));
    }

}
