package com.liyu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyu.component.Type;
import com.liyu.service.TypeService;
import com.liyu.mapper.TypeMapper;
import com.liyu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
* @author Misaki、
* @description 针对表【news_type】的数据库操作Service实现
* @createDate 2024-01-28 19:35:29
*/
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type>
    implements TypeService{

    @Autowired
    TypeMapper typeMapper;

    @Override
    public Result findAllTypes() {
        LambdaQueryWrapper<Type> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.select(Type::getTid).select(Type::getTname);
        List<Map<String, Object>> data = typeMapper.selectMaps(lambdaQueryWrapper);
        return Result.ok(data);
    }
}




