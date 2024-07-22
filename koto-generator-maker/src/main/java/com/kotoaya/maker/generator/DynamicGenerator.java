package com.kotoaya.maker.generator;

import cn.hutool.core.io.FileUtil;
import com.kotoaya.maker.config.FreeMarkerFactory;
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
    /**
     * 动态生成文件
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void doGenerate(String inputPath,String outputPath,Object data) throws IOException, TemplateException {
        Template template = FreeMarkerFactory.getTemplate(inputPath);
        //如果文件不存在就创建目录
        if (!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }
        Writer output=new FileWriter(outputPath);
        template.process(data,output);
        output.close();
    }
}
