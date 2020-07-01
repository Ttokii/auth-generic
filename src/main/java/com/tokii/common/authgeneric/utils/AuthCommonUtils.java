package com.tokii.common.authgeneric.utils;

import java.util.Objects;

public class AuthCommonUtils {

    public static String getToString(Object obj) {
        return Objects.isNull(obj) ? "" : obj.toString();
    }
}
