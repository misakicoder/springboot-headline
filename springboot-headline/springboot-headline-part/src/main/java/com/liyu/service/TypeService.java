package com.liyu.service;

import com.liyu.component.Type;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liyu.utils.Result;

/**
* @author Misaki、
* @description 针对表【news_type】的数据库操作Service
* @createDate 2024-01-28 19:35:29
*/
public interface TypeService extends IService<Type> {

    Result findAllTypes();
}
