package com.kotoaya.maker.model;

import lombok.Data;

/**
 * acm的静态模板配置
 * @author wzw
 */
@Data
public class DataModel {
    //作者
    private String author="default_user";
    //输出前缀
    private String outputText="Sum=：";
    //是否包含循环语句
    private boolean loop;
}
