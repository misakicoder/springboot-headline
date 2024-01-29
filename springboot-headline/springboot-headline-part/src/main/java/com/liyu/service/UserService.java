package com.liyu.service;

import com.liyu.component.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liyu.utils.Result;

/**
* @author Misaki、
* @description 针对表【news_user】的数据库操作Service
* @createDate 2024-01-28 19:35:29
*/
public interface UserService extends IService<User> {
    Result login(User user);

    Result getUserInfo(String token);

    Result checkUserName(String username);

    Result register(User user);

    Result checkLogin(String token);
}
