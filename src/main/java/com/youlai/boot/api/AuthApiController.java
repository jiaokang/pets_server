package com.youlai.boot.api;


import com.youlai.boot.auth.request.LoginParam;
import com.youlai.boot.auth.service.AuthService;
import com.youlai.boot.common.result.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    AuthService authService;

    /**
     * 账号密码登录
     */
    @PostMapping("/login")
    public ResponseEntity<Response<String>> login(@RequestBody LoginParam loginParam){
        String token = authService.loginByAccount(loginParam);
        return ResponseEntity.ok();
    }

}
