package com.kotoaya.maker.generator;

import cn.hutool.core.io.FileUtil;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFilePermission;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Set;

/**
 * @author kotoaya
 * 生成脚本文件
 */
public class ScriptGenerator {
    public static void doGenerator(String outputPath,String jarPath) {
        StringBuilder sb=new StringBuilder();
        //Linux脚本
        sb.append("#!/bin/bash").append("\n");
        sb.append(String.format("java -jar %s \"$@\"",jarPath));
        FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8),outputPath);
        try {
            //添加可执行权限
            Set<PosixFilePermission> permissions = PosixFilePermissions.fromString("rwxrwxrwx");
            //给对应文件添加权限
            Files.setPosixFilePermissions(Paths.get(outputPath),permissions);
        } catch (Exception ignored) {
            System.out.println("检测到非类Unix系统");
        }finally {
            //Windows脚本
            sb=new StringBuilder();
            sb.append("@echo off").append("\n");
            sb.append(String.format("java -jar %s %%*",jarPath));
            FileUtil.writeBytes(sb.toString().getBytes(StandardCharsets.UTF_8),outputPath+".bat");
        }
    }
}
