package ${basePackage}.config;

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
    private static String encoding="utf-8";

    /**
     * 初始化freemarker配置
     * @return Configuration
     */
    public static Configuration getFreeMarkerConfig(String templateDirectory) {
        Configuration cfg = new Configuration(Configuration.VERSION_2_3_33);
        //指定模板文件所在的路径
         try {
             cfg.setDirectoryForTemplateLoading(new File(templateDirectory));
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
     * @param templatePath 模板文件所在路径
     * @return Template
     */
    public static Template getTemplate(String templatePath){
        //判断是路径还是文件名
        if (!templatePath.contains("/") && !templatePath.contains("\\")){
            return getTemplateByName(templatePath);
        }
        //分离出模板路径和模板文件名
        String templateBasePath=new File(templatePath).getParent();
        String templateName = new File(templatePath).getName();
        Configuration freeMarkerConfig = getFreeMarkerConfig(templateBasePath);
        Template template=null;
        try {
            template = freeMarkerConfig.getTemplate(templateName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return template;
    }

    /**
     * 通过文件名获取template对象
     * @param templateName 模板名称
     * @return Template
     */
    public static Template getTemplateByName(String templateName){
        Configuration freeMarkerConfig = getFreeMarkerConfig("E:\\project\\koto-generator\\koto-generator-basic/src/main/resources/templates");
        Template template=null;
        try {
            template = freeMarkerConfig.getTemplate(templateName);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return template;
    }
}
