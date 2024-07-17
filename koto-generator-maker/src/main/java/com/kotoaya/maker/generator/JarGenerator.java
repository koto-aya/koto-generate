package com.kotoaya.maker.generator;

import java.io.*;

/**
 * @author kotoaya
 * 用于构建jar包
 */
public class JarGenerator {
    /**
     * 生成jar包
     * @param projectDir 要打成jar包的目录
     */
    public static void doGenerator(String projectDir) throws IOException, InterruptedException {
        //调用Process类执行maven命令
        //-DskipTests：跳过所有的测试用例
        String otherMavenCommand="mvn clean package -DskipTests=true";
        String winMavenCommand="mvn.cmd clean package -DskipTests=true";
        String mavenCommand=winMavenCommand;

        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));//指定路径
        Process process = processBuilder.start();
        //读取命令的输出
        InputStream inputStream = process.getInputStream();
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
        String line="";
        while((line=br.readLine())!=null){
            System.out.println(line);
        }

        int exitCode = process.waitFor();//调用命令，得到退出码
        System.out.println(exitCode);
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerator("E:\\project\\koto-generator\\koto-generator-maker\\generated");
    }
}
