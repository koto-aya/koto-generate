package com.kotoaya.maker.cli;

import com.kotoaya.maker.cli.command.ConfigCommand;
import com.kotoaya.maker.cli.command.GenerateCommand;
import com.kotoaya.maker.cli.command.ListCommand;
import picocli.CommandLine;

/**
 * @author wzw
 * 命令执行器
 */
@CommandLine.Command(name = "kotoaya",mixinStandardHelpOptions = true)
public class CommandExecutor implements Runnable{
    private final CommandLine commandLine;

    {
        commandLine=new CommandLine(this)
                .addSubcommand(ConfigCommand.class)
                .addSubcommand(GenerateCommand.class)
                .addSubcommand(ListCommand.class);
    }

    public void run() {
        System.out.println("请输入--help获取帮助手册");
    }

    public Integer doExecute(String[] args){
        return commandLine.execute(args);
    }
}
