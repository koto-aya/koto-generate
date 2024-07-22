package com.kotoaya.maker;

import com.kotoaya.maker.cli.CommandExecutor;
import com.kotoaya.maker.meta.Meta;
import com.kotoaya.maker.meta.MetaManager;
import com.kotoaya.maker.meta.MetaValidator;

public class Main {
    public static void main(String[] args) {
        CommandExecutor commandExecutor=new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}