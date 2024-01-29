package com.liyu.intercepter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.liyu.utils.JwtHelper;
import com.liyu.utils.Result;
import com.liyu.utils.ResultCodeEnum;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * ClassName:LoginProtectInterceptor
 * PackageName:com.liyu.intercepter
 * 题目：
 * Author:misaki
 * Create 2024/1/29 10:23
 * Version 1.0
 */
@Component
public class LoginProtectInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        boolean expiration = jwtHelper.isExpiration(token);
        if(!expiration){
            return true;
        }

        Result result = Result.build(null, ResultCodeEnum.NOTLOGIN);

        //json和对象相互转换
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(result);
        response.getWriter().print(json);
        return false;
    }
}
