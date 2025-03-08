package com.lwj.pets.api;


import com.lwj.pets.common.Result;
import com.lwj.pets.logic.AuthLogic;
import com.lwj.pets.req.LoginByEmailParam;
import com.lwj.pets.req.LoginParam;
import com.lwj.pets.req.RegisterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {

    @Autowired
    AuthLogic authLogic;


    /**
     * 注册
     */
    @PostMapping("/register")
    public Result<Void> register(@RequestBody RegisterParam registerParam) {
        authLogic.register(registerParam);
        return Result.success();
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestBody LoginParam loginParam) {
        HashMap<String, Object> map = authLogic.login(loginParam);
        return Result.success(map);
    }


    /**
     * 获取邮箱验证码
     */
    @GetMapping("/getEmailVerifyCode")
    public Result<Map<String, Object>> getEmailVerifyCode(@RequestParam("email") String email) {
        authLogic.getEmailVerifyCode(email);
        return Result.success();
    }


    /**
     * 登录
     */
    @PostMapping("/loginByEmail")
    public Result<Map<String, Object>> loginByEmail(@RequestBody LoginByEmailParam loginByEmailParam) {
        HashMap<String, Object> map = authLogic.loginByEmail(loginByEmailParam);
        return Result.success(map);
    }


    /**
     * 退出登录
     */
    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("Authorization") String token) {
        authLogic.logout(token);
        return Result.success();
    }

}
