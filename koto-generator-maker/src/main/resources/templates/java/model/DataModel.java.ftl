package ${basePackage}.model;

import lombok.Data;

/**
 * acm的静态模板配置
 * @author ${author}
 */
@Data
public class DataModel {
<#list modelConfig.models as modelInfo>

    <#if modelInfo.description??>
    /**
     * ${modelInfo.description}
     */
    </#if>
    private ${modelInfo.type} ${modelInfo.fieldName} <#if modelInfo.defaultValue??>=${modelInfo.defaultValue?c}</#if>;
</#list>
}
