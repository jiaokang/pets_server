package com.lwj.pets.api;


import com.lwj.pets.common.Result;
import com.lwj.pets.logic.AuthLogic;
import com.lwj.pets.logic.SettingLogic;
import com.lwj.pets.req.LoginByEmailParam;
import com.lwj.pets.req.LoginParam;
import com.lwj.pets.req.RegisterParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/setting")
public class SettingApiController {

    @Autowired
    SettingLogic settingLogic;


    /**
     * 获取设置项
     */
    @GetMapping("/get")
    public Result<Map<String, Object>> getSetting(@RequestHeader("Authorization") String token) {
        HashMap<String, Object> map = settingLogic.getSetting(token);
        return Result.success(map);
    }

    /**
     * 设置设置项
     */
    @PostMapping("/set")
    public Result<Map<String, Object>> setSetting(@RequestHeader("Authorization") String token,
                                                  @RequestBody Map<String, String> map) {
        String item = map.get("item");
        settingLogic.setSetting(token, item);
        return Result.success();
    }

}
