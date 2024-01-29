package com.liyu.controller;

import com.liyu.component.User;
import com.liyu.service.UserService;
import com.liyu.utils.Result;
import org.apache.el.parser.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:UserController
 * PackageName:com.liyu.controller
 * 题目：
 * Author:misaki
 * Create 2024/1/28 21:07
 * Version 1.0
 */
@CrossOrigin
@ResponseBody
@RequestMapping("user")
@Controller
public class UserController {

    @Autowired
    protected UserService userService;

    @PostMapping("login")
    public Result login(@RequestBody User user){
        Result result = userService.login(user);
        return result;
    }

    @GetMapping("getUserInfo")
    public Result getUserinfo(@RequestHeader String token){
        Result result = userService.getUserInfo(token);
        return result;
    }

    @PostMapping("checkUserName")
    public Result checkUserName(String username){
        Result result = userService.checkUserName(username);
        return result;
    }

    @PostMapping("regist")
    public Result register(@RequestBody User user){
        Result result = userService.register(user);
        return result;
    }

    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){
        Result result = userService.checkLogin(token);
        return result;
    }
}
