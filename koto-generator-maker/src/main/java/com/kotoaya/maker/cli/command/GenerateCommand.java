package com.kotoaya.maker.cli.command;

import com.kotoaya.maker.generator.MainGenerator;
import com.kotoaya.maker.model.DataModel;
import picocli.CommandLine;
import freemarker.template.TemplateException;
import java.io.IOException;

@CommandLine.Command(name = "generate",mixinStandardHelpOptions = true)
public class GenerateCommand implements Runnable{
    //作者
    @CommandLine.Option(names = {"-a","--author"},description = "作者",arity = "0..1",interactive = true,prompt = "请输入作者信息",echo = true)
    private String author="default_user";
    //输出前缀
    @CommandLine.Option(names = {"-o","--output"},description = "输出提示信息",arity = "0..1",interactive = true,prompt = "请输入提示信息",echo = true)
    private String outputText="Sum=：";
    //是否包含循环语句
    @CommandLine.Option(names = {"-l","--loop"},description = "是否包含循环语句",arity = "0..1",interactive = true,prompt = "是否包含循环语句(true/false)",echo = true)
    private boolean loop;

    public void run() {
        DataModel dataModel =new DataModel();
        dataModel.setLoop(this.loop);
        dataModel.setAuthor(this.author);
        dataModel.setOutputText(this.outputText);
//        try {
//            MainGenerator.doGenerate(dataModel);
//        } catch (TemplateException e) {
//            throw new RuntimeException(e);
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
    }
}
