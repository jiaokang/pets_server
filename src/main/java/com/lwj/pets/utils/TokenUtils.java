package com.lwj.pets.utils;

import cn.hutool.jwt.JWT;
import cn.hutool.jwt.JWTUtil;

/**
 * @Author ：焦康
 * @Date ：Created in 21:29 2025/3/2
 * @Desc ：
 */
public class TokenUtils {


    /**
     * 从token中获取主人ID
     */
    public static Integer getOwnerId(String token) {
        // 从token中获取主人ID
        JWT jwt = JWTUtil.parseToken(token);
        return Integer.valueOf(jwt.getPayload("id").toString());
    }
}
