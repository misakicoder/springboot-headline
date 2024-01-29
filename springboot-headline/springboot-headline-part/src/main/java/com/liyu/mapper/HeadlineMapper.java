package com.liyu.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.liyu.component.Headline;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liyu.component.vp.PortalVo;
import com.liyu.utils.Result;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

/**
* @author Misaki、
* @description 针对表【news_headline】的数据库操作Mapper
* @createDate 2024-01-28 19:35:29
* @Entity com.liyu.component.Headline
*/
public interface HeadlineMapper extends BaseMapper<Headline> {

    IPage<Map> selectMyPage(IPage page, @Param("portalVo") PortalVo portalVo);

    Map selectHeadlineDetail(Integer hid);
}




