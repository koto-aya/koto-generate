package com.kotoaya.maker.meta.enums;

import lombok.Getter;

/**
 * 异常信息枚举类
 */
@Getter
public enum MetaExceptionInfoEnum {
    FIELD_NAME_IS_EMPTY("未填写字段名"),
    FIELD_TYPE_IS_EMPTY("未填写字段类型"),
    SOURCE_ROOT_PATH_IS_EMPTY("source路径为空"),
    INPUT_PATH_IS_EMPTY("输入路径未指定");

    private final String message;

    MetaExceptionInfoEnum(String message){
        this.message=message;
    }
}
