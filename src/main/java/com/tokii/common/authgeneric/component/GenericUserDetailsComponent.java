package com.tokii.common.authgeneric.component;

import com.tokii.common.authgeneric.entity.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Collections;

/**
 * 用户检索
 */
@Component
public class GenericUserDetailsComponent implements UserDetailsService {

    @Autowired
    GenericPasswordEncoder genericPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = new SysUser("root", "111111", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        return sysUser;
    }
}
