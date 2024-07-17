package ${basePackage}.cli.command;

import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.ReflectUtil;
import ${basePackage}.model.DataModel;
import freemarker.template.utility.StringUtil;
import picocli.CommandLine;

import java.lang.reflect.Field;

/**
 * @author ${author}
 * 获取配置信息
 */
@CommandLine.Command(name = "config",mixinStandardHelpOptions = true)
public class ConfigCommand implements Runnable{
    /**
     * 配置信息
     * key：字段类型
     * value：字段名称
     */

    public void run() {
        Field[] fields = ReflectUtil.getFields(DataModel.class);
        int currentFieldLen=0;//当前字段的长度
        //最大字段长度
        int maxFieldLength=0;
        //寻找最长字段的长度
        for (Field field : fields) {
            String typeName=field.getType().toString();
            currentFieldLen=typeName.length();
            if (maxFieldLength<currentFieldLen){
                maxFieldLength=currentFieldLen;
            }
        }
        printConfigInfo(maxFieldLength<<1,"",fields);
    }

    /**
     * @author ${author}
     * 打印配置信息
     * @param colLen 列长度
     * @param borderStyle 边框样式
     * @param fields 配置类字段
     */
    public void printConfigInfo(int colLen,String borderStyle,Field[] fields){
        if (ArrayUtil.isEmpty(fields)){
            throw new RuntimeException("config info initialization failed");
        }
        System.out.println(StringUtil.rightPad("",colLen*2,"-"));
        System.out.println(StringUtil.rightPad("|type", colLen)+"|name");
        System.out.println(StringUtil.rightPad("",colLen*2,"-"));
        for (Field row : fields) {
            System.out.println(StringUtil.rightPad("|"+row.getType(),colLen)+"|"+row.getName());
        }
        System.out.println(StringUtil.rightPad("",colLen*2,"-"));
    }
}
