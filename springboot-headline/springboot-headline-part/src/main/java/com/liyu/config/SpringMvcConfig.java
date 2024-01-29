package com.liyu.config;

import com.liyu.intercepter.LoginProtectInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * ClassName:SpringMvcConfig
 * PackageName:com.liyu.config
 * 题目：
 * Author:misaki
 * Create 2024/1/29 10:28
 * Version 1.0
 */
@Configuration
public class SpringMvcConfig implements WebMvcConfigurer {
    @Autowired
    LoginProtectInterceptor loginProtectInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginProtectInterceptor).addPathPatterns("/headline/**");
    }
}
