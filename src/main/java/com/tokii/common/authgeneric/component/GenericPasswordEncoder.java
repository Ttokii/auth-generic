package com.tokii.common.authgeneric.component;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class GenericPasswordEncoder extends BCryptPasswordEncoder {

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
//        super.matches(rawPassword, encodedPassword);
        return StringUtils.equals(rawPassword, encodedPassword);
    }
}
