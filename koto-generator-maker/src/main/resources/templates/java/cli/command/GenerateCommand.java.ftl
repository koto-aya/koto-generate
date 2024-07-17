package ${basePackage}.cli.command;

import ${basePackage}.model.DataModel;
import ${basePackage}.generator.MainGenerator;
import picocli.CommandLine;
import freemarker.template.TemplateException;
import java.io.IOException;

@CommandLine.Command(name = "generate",mixinStandardHelpOptions = true)
public class GenerateCommand implements Runnable{
<#list modelConfig.models as modelInfo>
    <#if modelInfo.description??>
    //${modelInfo.description}
    </#if>
    @CommandLine.Option(names = {"-${modelInfo.abbr}","--${modelInfo.fieldName}"},<#if modelInfo.description??>description="${modelInfo.description}：",</#if>arity = "0..1",interactive = true,<#if modelInfo.description??>prompt="${modelInfo.description}",</#if>echo = true)
    private ${modelInfo.type} ${modelInfo.fieldName} <#if modelInfo.defaultValue??> = ${modelInfo.defaultValue?c}</#if>;

</#list>
    public void run() {
        DataModel dataModel =new DataModel();
        dataModel.setLoop(this.loop);
        dataModel.setAuthor(this.author);
        dataModel.setOutputText(this.outputText);
        try {
            MainGenerator.doGenerate(dataModel);
        } catch (TemplateException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }
}
