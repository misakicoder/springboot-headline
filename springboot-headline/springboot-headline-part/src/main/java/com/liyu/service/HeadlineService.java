package com.liyu.service;

import com.liyu.component.Headline;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liyu.component.vp.PortalVo;
import com.liyu.utils.Result;

/**
* @author Misaki、
* @description 针对表【news_headline】的数据库操作Service
* @createDate 2024-01-28 19:35:29
*/
public interface HeadlineService extends IService<Headline> {

    Result findNewsPage(PortalVo portalVo);

    Result showHeadlineDetail(Integer hid);

    Result publish(Headline headline,String token);

    Result updateData(Headline headline);
}
