package com.kotoaya.maker.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

/**
 * @author wzw
 * 列出 文件列表
 */
@CommandLine.Command(name="list",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{
    public void run() {
        String rootPath=System.getProperty("user.dir");
        String inputPath=rootPath+ File.separator+"koto-generator-demo-project"+File.separator+"acm-template";
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }
    }
}
