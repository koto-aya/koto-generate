package ${basePackage}.cli.command;

import cn.hutool.core.io.FileUtil;
import picocli.CommandLine;

import java.io.File;
import java.util.List;

/**
 * @author ${author}
 * 列出 文件列表
 */
@CommandLine.Command(name="list",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{
    public void run() {
        String inputPath="${fileConfig.inputRootPath}";
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files) {
            System.out.println(file);
        }
    }
}
