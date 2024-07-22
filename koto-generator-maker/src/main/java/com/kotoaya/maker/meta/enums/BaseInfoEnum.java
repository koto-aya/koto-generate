package com.kotoaya.maker.meta.enums;

import cn.hutool.core.date.DateUtil;
import lombok.Getter;

/**
 * 基础信息枚举类
 */
@Getter
public enum BaseInfoEnum {
    NAME("项目名称","my-generator"),
    DESCRIPTION("项目描述","我的模板代码生成器"),
    BASE_PACKAGE("基础包路径","com.xiwu"),
    VERSION("版本","1.0"),
    AUTHOR("作者","夕雾"),
    CREATE_TIME("创建时间", DateUtil.now());

    private final String description;
    private final String value;

    BaseInfoEnum(String description,String value){
        this.description=description;
        this.value=value;
    }
}
