package com.tokii.common.authgeneric.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.sql.Blob;
import java.sql.Time;
import java.util.Collection;

public class SysUser extends User {
    private Long id;
    private String userName;
    private String userPassword;
    private String userEmail;
    private String userInfo;
    private Blob headImg;
    private Time createTime;

    public SysUser(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
        this.userName = username;
        this.userPassword = password;
    }
}
