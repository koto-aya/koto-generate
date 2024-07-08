package com.kotoaya.generator;

import com.kotoaya.config.FreeMarkerFactory;
import com.kotoaya.model.AcmModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 动态文件生成器
 * @author wzw
 */
public class DynamicGenerator {
    public static void main(String[] args) throws IOException, TemplateException {
        doGenerate("acm/MainTemplate.java.ftl","temp/MainTemplate.java");
    }

    /**
     * 动态生成文件
     * @param templateName 模板文件名
     * @param outputPath 输出路径
     */
    public static void doGenerate(String templateName,String outputPath) throws IOException, TemplateException {
        Template template = FreeMarkerFactory.getTemplate(templateName);
        AcmModel acmModel=new AcmModel();
        acmModel.setAuthor("kotoaya");
        acmModel.setOutputText("求和为：");
        acmModel.setLoop(true);
        Writer output=new FileWriter(outputPath);
        template.process(acmModel,output);
        output.close();
    }
}
