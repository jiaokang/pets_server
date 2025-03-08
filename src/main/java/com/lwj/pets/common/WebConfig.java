package com.lwj.pets.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author ：焦康
 * @Date ：Created in 11:57 2025/3/8
 * @Desc ：
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TokenRefreshInterceptor tokenRefreshInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenRefreshInterceptor)
                .addPathPatterns("/api/**"); // 只对/api/开头的路径应用该拦截器
    }
}
