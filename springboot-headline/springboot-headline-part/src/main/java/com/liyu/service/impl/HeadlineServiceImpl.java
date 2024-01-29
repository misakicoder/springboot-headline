package com.liyu.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liyu.component.Headline;
import com.liyu.component.vp.PortalVo;
import com.liyu.service.HeadlineService;
import com.liyu.mapper.HeadlineMapper;
import com.liyu.utils.JwtHelper;
import com.liyu.utils.Result;
import com.liyu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Misaki、
* @description 针对表【news_headline】的数据库操作Service实现
* @createDate 2024-01-28 19:35:29
*/
@Service
public class HeadlineServiceImpl extends ServiceImpl<HeadlineMapper, Headline>
    implements HeadlineService{

    @Autowired
    private HeadlineMapper headlineMapper;

    @Autowired
    private JwtHelper jwtHelper;

    @Override
    public Result findNewsPage(PortalVo portalVo) {
        IPage<Map> iPage = new Page<>(portalVo.getPageNum(), portalVo.getPageSize());
        headlineMapper.selectMyPage(iPage,portalVo);
        List<Map> records = iPage.getRecords();
        Map data = new HashMap<>();
        data.put("pageData",records);
        data.put("pageNum",iPage.getCurrent());
        data.put("pageSize",iPage.getSize());
        data.put("totalPage",iPage.getPages());
        data.put("totalSize",iPage.getTotal());

        Map pageInfo = new HashMap();
        pageInfo.put("pageInfo",data);
        return Result.ok(pageInfo);
    }

    @Override
    public Result showHeadlineDetail(Integer hid) {
        Map data = headlineMapper.selectHeadlineDetail(hid);
        Map headline = new HashMap<>();
        headline.put("headline",data);

        Headline headline1 = new Headline();
        headline1.setHid((Integer) data.get("hid"));
        headline1.setVersion((Integer) data.get("version"));
        headline1.setPageViews((Integer) data.get("pageViews") + 1);
        headlineMapper.updateById(headline1);
        return Result.ok(headline);
    }

    @Override
    public Result publish(Headline headline,String token) {
        System.out.println(headline);
        int userId = jwtHelper.getUserId(token).intValue();
        headline.setPublisher(userId);
        headline.setPageViews(0);
        headline.setCreateTime(new Date());
        headline.setUpdateTime(new Date());
        headlineMapper.insert(headline);
        return Result.build(null, ResultCodeEnum.SUCCESS);
    }

    @Override
    public Result updateData(Headline headline) {
        Integer version = headlineMapper.selectById(headline.getHid()).getVersion();
        headline.setVersion(version);
        headline.setUpdateTime(new Date());
        headlineMapper.updateById(headline);
        return Result.build(null,ResultCodeEnum.SUCCESS);
    }
}




