package com.kotoaya.maker;

import com.kotoaya.maker.cli.CommandExecutor;

public class Main {
    public static void main(String[] args) {
        CommandExecutor commandExecutor=new CommandExecutor();
        commandExecutor.doExecute(args);
    }
}