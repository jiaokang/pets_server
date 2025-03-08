package com.lwj.pets.common;

import com.lwj.pets.exception.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.concurrent.TimeUnit;

import static com.lwj.pets.common.ResultCode.TOKEN_ERROR;

/**
 * @Author ：焦康
 * @Date ：Created in 11:50 2025/3/8
 * @Desc ：
 */
@Component
public class TokenRefreshInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 获取请求头中的token
        String token = request.getHeader("Authorization");

        Object cacheToken = redisTemplate.opsForValue().get(CommonConst.TOKEN_REDIS_PREFIX + token);
        if (cacheToken==null){
            throw new BusinessException(TOKEN_ERROR);
        }
        Long expire = redisTemplate.getExpire(token, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(CommonConst.TOKEN_REDIS_PREFIX + token, cacheToken, expire, TimeUnit.MINUTES);
        return true;
    }
}
