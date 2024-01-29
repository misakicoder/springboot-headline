package com.liyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyu.component.User;
import com.liyu.service.UserService;
import com.liyu.mapper.UserMapper;
import com.liyu.utils.JwtHelper;
import com.liyu.utils.MD5Util;
import com.liyu.utils.Result;
import com.liyu.utils.ResultCodeEnum;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
* @author Misaki、
* @description 针对表【news_user】的数据库操作Service实现
* @createDate 2024-01-28 19:35:29
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    @Autowired
    public UserMapper userMapper;

    @Autowired
    public JwtHelper jwtHelper;
    @Override
    public Result login(User user) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,user.getUsername());
        User loginUser = userMapper.selectOne(lambdaQueryWrapper);
        if(loginUser == null){
            return Result.build(null, ResultCodeEnum.USERNAME_ERROR);
        }
        //对比密码
        if(!StringUtils.isNullOrEmpty(user.getUserPwd())
                && MD5Util.encrypt(user.getUserPwd()).equals(loginUser.getUserPwd())){
            String token = jwtHelper.createToken(Long.valueOf(loginUser.getUid()));
            HashMap data = new HashMap<>();
            data.put("token",token);
            return Result.build(data,ResultCodeEnum.SUCCESS);
        }
        return Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
    }

    @Override
    public Result getUserInfo(String token) {
        if(jwtHelper.isExpiration(token)){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        Integer userId = jwtHelper.getUserId(token).intValue();
        User user = userMapper.selectById(userId);
        user.setUserPwd("");
        HashMap data = new HashMap();
        data.put("loginUser",user);
        return Result.ok(data);
    }

    @Override
    public Result checkUserName(String username) {
        LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.eq(User::getUsername,username);
        Long l = userMapper.selectCount(lambdaQueryWrapper);
        if(l >0){
            return Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        return Result.ok(null);
    }

    @Override
    public Result register(User user) {
        user.setUserPwd(MD5Util.encrypt(user.getUsername()));
        userMapper.insert(user);
        return Result.ok(null);
    }

    @Override
    public Result checkLogin(String token) {
        boolean isExpiration = jwtHelper.isExpiration(token);
        if(isExpiration){
            return Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        return Result.ok(null);
    }
}




