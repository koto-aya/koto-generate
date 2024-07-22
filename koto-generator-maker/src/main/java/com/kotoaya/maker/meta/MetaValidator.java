package com.kotoaya.maker.meta;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjUtil;
import cn.hutool.core.util.StrUtil;
import com.kotoaya.maker.meta.enums.BaseInfoEnum;
import com.kotoaya.maker.meta.enums.MetaExceptionInfoEnum;
import com.kotoaya.maker.meta.enums.ModelTypeEnum;

import java.nio.file.Paths;
import java.util.List;

/**
 * @author kotoaya
 * 元数据校验类
 */
public class MetaValidator {
    /**
     * 校验字段并填充
     * @param meta 元数据类
     */
    public static void doValidAndFill(Meta meta){
        validAndFillBaseInfo(meta);
        validAndFillFileConfig(meta);
        validAndFillModelConfig(meta);
    }

    /**
     * 校验元数据配置类
     * @param meta 元数据类
     */
    private static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null) {
            return;
        }
        List<Meta.ModelConfig.ModelInfo> models = modelConfig.getModels();
        if (CollUtil.isEmpty(models)) {
            return;
        }
        for (Meta.ModelConfig.ModelInfo model : models) {
            String fieldName = model.getFieldName();
            String modelType = model.getType();
            if (StrUtil.isBlank(fieldName)){
                throw new MetaException(MetaExceptionInfoEnum.FIELD_NAME_IS_EMPTY.getMessage());
            }
            if (StrUtil.isBlank(modelType)){
                throw new MetaException(MetaExceptionInfoEnum.FIELD_TYPE_IS_EMPTY.getMessage());
            }
            //model字段的描述信息
            String modelDescription = StrUtil.blankToDefault(model.getDescription(),"");
            model.setDescription(modelDescription);
            //model字段的缩写
            String abbr = StrUtil.blankToDefault(model.getAbbr(),fieldName.substring(0, 1));
            model.setAbbr(abbr);
            //model字段的默认值
            Object defaultValue = model.getDefaultValue();
            if (ObjUtil.isEmpty(defaultValue)){
                initModelDefaultValueByType(model, modelType);
            }
        }
    }

    /**
     * 校验文件配置类
     * @param meta 元数据类
     */
    private static void validAndFillFileConfig(Meta meta) {
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null) {
            return;
        }
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)){
            throw new MetaException(MetaExceptionInfoEnum.SOURCE_ROOT_PATH_IS_EMPTY.getMessage());
        }
        //inputRootPath= .source + sourceRootPath的最后一个层级
        String inputRootPath = StrUtil.blankToDefault(fileConfig.getInputRootPath(),".source/"+ FileUtil.getLastPathEle(Paths.get(sourceRootPath)));
        fileConfig.setInputRootPath(inputRootPath);
        //输出根路径
        String outputRootPath = StrUtil.blankToDefault(fileConfig.getOutputRootPath(),"generated");
        fileConfig.setOutputRootPath(outputRootPath);
        List<Meta.FileConfig.FileInfo> files = fileConfig.getFiles();
        if (CollUtil.isEmpty(files)) {
            return;
        }
        for (Meta.FileConfig.FileInfo file : files) {
            String inputPath = file.getInputPath();
            if (StrUtil.isBlank(inputPath)){
                throw new MetaException(MetaExceptionInfoEnum.INPUT_PATH_IS_EMPTY.getMessage());
            }
            //默认outputPath等于inputPath
            String outputPath = StrUtil.blankToDefault(file.getOutputPath(),inputPath);
            file.setOutputPath(outputPath);
            //如果inputPath有文件后缀，type为file，否则为dir
            String type = file.getType();
            if (StrUtil.isBlank(type)){
                type = getFileType(inputPath);
                file.setType(type);
            }
            //文件结尾如果为.ftl，generateType为dynamic，否则为static
            String generateType = file.getGenerateType();
            if (StrUtil.isBlank(generateType)){
                generateType = getGenerateFileType(inputPath);
                file.setGenerateType(generateType);
            }
        }
    }

    /**
     * 基础信息校验
     * @param meta
     */
    private static void validAndFillBaseInfo(Meta meta) {
        String name =StrUtil.blankToDefault(meta.getName(), BaseInfoEnum.NAME.getValue());
        meta.setName(name);
        String description = StrUtil.blankToDefault(meta.getDescription(), BaseInfoEnum.DESCRIPTION.getValue());
        meta.setDescription(description);
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(),BaseInfoEnum.BASE_PACKAGE.getValue());
        meta.setBasePackage(basePackage);
        String version = StrUtil.blankToDefault(meta.getVersion(),BaseInfoEnum.VERSION.getValue());
        meta.setVersion(version);
        String author = StrUtil.blankToDefault(meta.getAuthor(),BaseInfoEnum.AUTHOR.getValue());
        meta.setAuthor(author);
        String createTime = StrUtil.blankToDefault(meta.getCreateTime(),BaseInfoEnum.CREATE_TIME.getValue());
        meta.setCreateTime(createTime);
    }

    /**
     * 根据类型初始化model中字段的默认值
     * @param model
     * @param modelType
     */
    private static void initModelDefaultValueByType(Meta.ModelConfig.ModelInfo model, String modelType) {
        Object defaultValue;
        if (ModelTypeEnum.BOOLEAN.getTypeName().equals(modelType)){
            defaultValue=ModelTypeEnum.BOOLEAN.getDefaultValue();
        }else if(ModelTypeEnum.STRING.getTypeName().equals(modelType)){
            defaultValue=ModelTypeEnum.STRING.getDefaultValue();
        }else {
            defaultValue=null;
        }
        model.setDefaultValue(defaultValue);
    }

    /**
     * 根据文件后缀获取文件类型
     * @param filePath 文件路径
     */
    private static String getFileType(String filePath){
        String suffix = FileUtil.getSuffix(filePath);
        //如果不存在文件后缀，type为dir
        return StrUtil.isBlank(suffix)?"dir":"file";
    }

    /**
     * 根据文件后缀获取生成文件类型
     * @param filePath 文件路径
     */
    private static String getGenerateFileType(String filePath){
        String suffix = FileUtil.getSuffix(filePath);
        //如果文件后缀为ftl，返回dynamic，否则返回static
        return "ftl".equals(suffix)?"dynamic":"static";
    }
}
