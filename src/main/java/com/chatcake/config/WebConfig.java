package com.chatcake.config;

import com.chatcake.interceptor.LoggerInterceptor;
import com.chatcake.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private String [] OPEN_ENDPOINTS = {
            "/api/auth/login",
            "/api/auth/register"
    };

    @Autowired private TokenInterceptor tokenInterceptor;
    @Autowired private LoggerInterceptor loggerInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loggerInterceptor);
        registry.addInterceptor(tokenInterceptor).excludePathPatterns(OPEN_ENDPOINTS);
    }
}
