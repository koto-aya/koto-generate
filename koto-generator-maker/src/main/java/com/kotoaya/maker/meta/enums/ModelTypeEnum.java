package com.kotoaya.maker.meta.enums;

import lombok.Getter;

/**
 * 元数据的类型枚举类
 */
@Getter
public enum ModelTypeEnum {
    STRING("String",""),
    BOOLEAN("boolean",false);

    private final String typeName;
    private final Object defaultValue;

    ModelTypeEnum(String typeName,Object defaultValue){
        this.typeName=typeName;
        this.defaultValue=defaultValue;
    }
}
