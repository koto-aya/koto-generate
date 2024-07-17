package ${basePackage}.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;

/**
 * @author ${author}
 * 静态文件生成器
 */
public class StaticGenerator {
    //项目的根路径
    private static final String ROOT_PATH=System.getProperty("user.dir");
    public static void main(String[] args) {
        //输入路径
        String inputPath=ROOT_PATH+ File.separator+"koto-generator-demo-project"+File.separator+"acm-template";
        //输出路径
        String outputPath=ROOT_PATH+File.separator+"temp";
        copyFileByRecursive(new File(inputPath),new File(outputPath));
    }

    /**
     * 通过hutool工具类静态生成文件
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFilesByHutool(String inputPath,String outputPath){
        FileUtil.copy(inputPath,outputPath,false);
    }

    /**
     * 通过递归的方式静态生成文件
     * @param inputPath 输入路径
     * @param outputPath 输出路径
     */
    public static void copyFileByRecursive(File inputPath,File outputPath){
        //判断是否为目录
        if (inputPath.isDirectory()){
            File descPath = new File(outputPath, inputPath.getName());
            //在目标位置创建一个对应目录
            if (!descPath.exists()){
                descPath.mkdir();
            }
            //获取目录下的所有文件及目录
            File[] files = inputPath.listFiles();
            //无子文件，直接退出
            if (ArrayUtil.isEmpty(files)){
                return;
            }
            //递归创建子文件或子文件夹
            for (File file:files){
                copyFileByRecursive(file,descPath);
            }
        }else{
            //如果为文件，直接copy过去
            String descPath= outputPath.getAbsolutePath()+File.separator+inputPath.getName();
            FileUtil.copy(inputPath.getAbsolutePath(),descPath,false);
        }
    }
}
