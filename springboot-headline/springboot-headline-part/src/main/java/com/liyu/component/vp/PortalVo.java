package com.liyu.component.vp;

import lombok.Data;

/**
 * ClassName:PortalVo
 * PackageName:com.liyu.component.vp
 * 题目：
 * Author:misaki
 * Create 2024/1/29 8:44
 * Version 1.0
 */
@Data
public class PortalVo {
    private String keyWords;
    private int type = 0;
    private int pageNum = 1;
    private int pageSize = 10;
}
