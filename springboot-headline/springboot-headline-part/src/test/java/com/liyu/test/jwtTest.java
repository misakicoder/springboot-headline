package com.liyu.test;

import com.liyu.utils.JwtHelper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * ClassName:jwtTest
 * PackageName:com.liyu.test
 * 题目：
 * Author:misaki
 * Create 2024/1/28 20:49
 * Version 1.0
 */
@SpringBootTest
public class jwtTest {
    @Autowired
    public JwtHelper jwtHelper;

    @Test
    public void test(){
        String token = jwtHelper.createToken(1L);

        int userId = (jwtHelper.getUserId(token)).intValue();
        System.out.println("userId :" + userId);

        boolean expiration = jwtHelper.isExpiration(token);
        System.out.println("expiration :" + expiration);
    }
}
