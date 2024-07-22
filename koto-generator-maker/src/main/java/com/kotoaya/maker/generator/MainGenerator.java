package com.kotoaya.maker.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.io.resource.ResourceUtil;
import cn.hutool.core.util.StrUtil;
import com.kotoaya.maker.meta.Meta;
import com.kotoaya.maker.meta.MetaManager;
import com.kotoaya.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

public class MainGenerator {

    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        Meta meta = MetaManager.getMeta();
        //输出的根路径
        String rootPath = System.getProperty("user.dir");
        String outputPath = rootPath + File.separator + "generated/acm-template-pro";
        //精简版路径
        String distOutputPath=outputPath+"-dist";
        //如果这个目录不存在，就创建目录
        if (!FileUtil.exist(outputPath)) {
            FileUtil.mkdir(outputPath);
        }
        if (!FileUtil.exist(distOutputPath)){
            FileUtil.mkdir(distOutputPath);
        }

        //从原始文件模板路径复制到生成的代码包中
        String sourceRootPath= meta.getFileConfig().getSourceRootPath();//模板文件所在的原始路径
        String sourceCopyDestPath=outputPath+File.separator+".source";
        FileUtil.copy(sourceRootPath,sourceCopyDestPath,false);
        //复制一份到精简包中
        FileUtil.copy(sourceCopyDestPath,distOutputPath,false);

        //读取resources目录
        ClassPathResource classPathResource = new ClassPathResource("");
        String inputResourcePath = classPathResource.getAbsolutePath();
        //java包的基础路径
        String basePackage = meta.getBasePackage();//com.kotoaya
        String basePackagePath = basePackage.replaceAll("\\.", "/");//com/kotoaya
        //generated/src/main/java/com/kotoaya/xxx
        String outputBaseJavaPackagePath=outputPath+File.separator+"src/main/java/"+basePackagePath;

        String inputFilePath;//输入路径
        String outputFilePath;//输出路径
        //dataModel
        inputFilePath=inputResourcePath+File.separator+"templates/java/model/DataModel.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"model/DataModel.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //configCommand
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/command/ConfigCommand.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"cli/command/ConfigCommand.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //generateCommand
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/command/GenerateCommand.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"cli/command/GenerateCommand.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //listCommand
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/CommandExecutor.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"cli/CommandExecutor.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //CommandExecutor
        inputFilePath=inputResourcePath+File.separator+"templates/java/cli/command/ListCommand.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"cli/command/ListCommand.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //main
        inputFilePath=inputResourcePath+File.separator+"templates/java/Main.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"Main.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //MainGenerator
        inputFilePath=inputResourcePath+File.separator+"templates/java/generator/MainGenerator.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"generator/MainGenerator.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //DynamicGenerator
        inputFilePath=inputResourcePath+File.separator+"templates/java/generator/DynamicGenerator.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"generator/DynamicGenerator.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //StaticGenerator
        inputFilePath=inputResourcePath+File.separator+"templates/java/generator/StaticGenerator.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"generator/StaticGenerator.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //FreeMarkerFactory
        inputFilePath=inputResourcePath+File.separator+"templates/java/config/FreeMarkerFactory.java.ftl";
        outputFilePath=outputBaseJavaPackagePath+File.separator+"config/FreeMarkerFactory.java";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //pom.xml
        inputFilePath=inputResourcePath+File.separator+"templates/pom.xml.ftl";
        outputFilePath=outputPath+File.separator+"pom.xml";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);
        //README
        inputFilePath=inputResourcePath+File.separator+"templates/README.md.ftl";
        outputFilePath=outputPath+File.separator+"README.md";
        DynamicGenerator.doGenerate(inputFilePath,outputFilePath,meta);

        //构建jar包
        JarGenerator.doGenerator(outputPath);
        //jar包名称
        String jarName = meta.getName() + "-" + meta.getVersion() + "-jar-with-dependencies.jar";
        String jarPath=outputPath+File.separator+"target/"+jarName;
        //输出脚本路径
        String outputScriptPath=outputPath+File.separator+"generator";
        ScriptGenerator.doGenerator(outputScriptPath,jarPath);
        //将jar包和脚本文件复制到精简包中
        FileUtil.copy(jarPath,distOutputPath,false);
        jarPath=distOutputPath+File.separator+jarName;
        String outputDistScriptPath=distOutputPath+File.separator+"generator";
        ScriptGenerator.doGenerator(outputDistScriptPath,jarPath);
        //将readme文件复制到精简包中
        FileUtil.copy(outputPath+File.separator+"README.md",distOutputPath,true);
    }
}
