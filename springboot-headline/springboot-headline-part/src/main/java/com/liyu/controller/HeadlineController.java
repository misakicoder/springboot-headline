package com.liyu.controller;

import com.liyu.component.Headline;
import com.liyu.service.HeadlineService;
import com.liyu.utils.Result;
import com.liyu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName:HeadlineController
 * PackageName:com.liyu.controller
 * 题目：
 * Author:misaki
 * Create 2024/1/29 10:12
 * Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("headline")
public class HeadlineController {
    @Autowired
    HeadlineService headlineService;

    @PostMapping("publish")
    public Result publish(@RequestBody Headline headline,@RequestHeader String token){
        Result result = headlineService.publish(headline, token);
        return result;
    }

    @PostMapping("findHeadlineByHid")
    public Result findHeadlineByHid(Integer id){
        Headline headline = headlineService.getById(id);
        Map data = new HashMap<>();
        data.put("headline",data);
        return Result.build(data, ResultCodeEnum.SUCCESS);
    }

    @PostMapping("update")
    public Result update(@RequestBody Headline headline){
        Result result = headlineService.updateData(headline);
        return result;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(Integer hid){
        headlineService.removeById(hid);
        return Result.ok(null);
    }

}
