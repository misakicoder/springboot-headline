package com.liyu.controller;

import com.liyu.component.vp.PortalVo;
import com.liyu.service.HeadlineService;
import com.liyu.service.TypeService;
import com.liyu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * ClassName:PortalController
 * PackageName:com.liyu.controller
 * 题目：
 * Author:misaki
 * Create 2024/1/29 8:14
 * Version 1.0
 */
@ResponseBody
@Controller
@RequestMapping("portal")
public class PortalController {

    @Autowired
    public TypeService typeService;

    @Autowired
    public HeadlineService headlineService;

    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result allTypes = typeService.findAllTypes();
        return allTypes;
    }

    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody PortalVo portalVo){
        Result newsPage = headlineService.findNewsPage(portalVo);
        return newsPage;
    }

    @PostMapping("showHeadlineDetail")
    public Result showHeadlineDetail(Integer hid){
        Result result = headlineService.showHeadlineDetail(hid);
        return result;
    }
}
