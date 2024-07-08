package com.kotoaya.config;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;
import lombok.Getter;
import lombok.Setter;

import java.io.File;
import java.io.IOException;
import java.util.TimeZone;

/**
 * freemarker配置
 * 参考文档：<a href="https://freemarker.apache.org/docs/pgui_quickstart_createconfiguration.html">...</a>
 */
@Setter
@Getter
public class FreeMarkerFactory {
    private static final String rootPath=System.getProperty("user.dir");
    private static String templateDirectory="koto-generator-basic/src/main/resources/templates";
    private static String encoding="utf-8";

    /**
     * 初始化freemarker配置
     * @return Configuration
     */
    public static Configuration getFreeMarkerConfig() {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_33);
        //指定模板文件所在的路径
         try {
             cfg.setDirectoryForTemplateLoading(new File(rootPath,templateDirectory));
         } catch (IOException e) {
             throw new RuntimeException(e);
         }
         //设置字符集
        cfg.setDefaultEncoding(encoding);
        cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        cfg.setLogTemplateExceptions(false);
        cfg.setWrapUncheckedExceptions(true);
        cfg.setFallbackOnNullLoopVariable(false);
        cfg.setSQLDateAndTimeTimeZone(TimeZone.getDefault());
        return cfg;
    }

    /**
     * 加载指定模板
     * @param fileName 模板文件名
     * @return Template
     */
    public static Template getTemplate(String fileName){
        Configuration freeMarkerConfig = getFreeMarkerConfig();
        Template template=null;
        try {
            template = freeMarkerConfig.getTemplate(fileName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return template;
    }
}
