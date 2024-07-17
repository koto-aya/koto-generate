package com.kotoaya.maker.meta;

import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.json.JSONUtil;

/**
 * @author xiwu
 * 用于读取meta元数据的类
 */
public class MetaManager {
    private static volatile Meta meta;

    public static Meta getMeta(){
        if (meta==null){
            synchronized (MetaManager.class) {
                if (meta==null){
                    meta = initMeta();
                }
            }
        }
        return meta;
    }

    private static Meta initMeta(){
        //读取meta.json内容，并将读取到的json数据转换为java对象
        String metaJson = ResourceUtil.readUtf8Str("meta.json");
        Meta meta = JSONUtil.toBean(metaJson, Meta.class);
        return meta;
    }
}
