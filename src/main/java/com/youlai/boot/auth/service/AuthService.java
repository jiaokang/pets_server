package com.youlai.boot.auth.service;


import com.youlai.boot.auth.request.LoginParam;
import com.youlai.boot.core.security.manager.JwtTokenManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    JwtTokenManager jwtTokenManager;

    public String loginByAccount(LoginParam loginParam) {
        jwtTokenManager.generateToken(loginParam.getUsername(), 3600);
    }
}
