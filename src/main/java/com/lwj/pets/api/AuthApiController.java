package com.lwj.pets.api;


import com.lwj.pets.common.Result;
import com.lwj.pets.logic.AuthLogic;
import com.lwj.pets.req.LoginParam;
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
     * 登录
     */
    @PostMapping("/login")
    public Result<Map<String,Object>> login(@RequestBody LoginParam loginParam){
        HashMap<String,Object> map = authLogic.login(loginParam);
        return Result.success(map);
    }
}
