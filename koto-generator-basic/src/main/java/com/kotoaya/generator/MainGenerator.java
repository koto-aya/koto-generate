package com.kotoaya.generator;

import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * @author wzw
 * 动静结合生成器
 */
public class MainGenerator {
    private static final String ROOT_PATH=System.getProperty("user.dir");
    public static void main(String[] args) throws TemplateException, IOException {
        //静态文件生成
        //输入路径
        String inputPath=ROOT_PATH+ File.separator+"koto-generator-demo-project"+File.separator+"acm-template";
        //输出路径
        String outputPath=ROOT_PATH+File.separator+"temp";
        StaticGenerator.copyFileByRecursive(new File(inputPath),new File(outputPath));
        //动态文件生成
        String dynamicOutputPath=outputPath+File.separator+"acm-template/src/com/koto/acm/MainTemplate.java";
        DynamicGenerator.doGenerate("acm/MainTemplate.java.ftl",dynamicOutputPath);
    }
}
