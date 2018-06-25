package com.xiangzhurui.drools.util;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.drools.compiler.compiler.DrlParser;
import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.lang.descr.*;
import org.kie.api.KieServices;
import org.kie.api.io.Resource;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author xiangzhurui
 * @version 2018/6/8 15:17
 */
@Slf4j
public class DrlFormat {
    private DrlFormat() {
    }

    public static String formatFrom(String input) {

        if (StringUtils.isBlank(input)) {
            return "";
        }
        if (StringUtils.isNotBlank(input) && !input.startsWith("package")) {
            throw new IllegalArgumentException("input format illegal");
        }

        StringBuilder builder = new StringBuilder();
        String pkg = input.substring(0, input.indexOf(";"));
        List<String> importList = Lists.newArrayList();

        return builder.toString();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, DroolsParserException {
        Resource resource = KieServices.get().getResources().newClassPathResource("rules/test.drl");

        DrlParser drlParser = new DrlParser();
        PackageDescr packageDescr = drlParser.parse(resource);
        String drl = packageDescr.toString();
        System.out.println(drl);
        System.out.println(ToStringBuilder.reflectionToString(packageDescr, ToStringStyle.MULTI_LINE_STYLE));
        StringBuilder builder = new StringBuilder();

        builder.append(packageDescr.toString()).append(";\n");
        for (ImportDescr importDescr : packageDescr.getImports()) {
            builder.append(importDescr).append(";\n");
        }
        for (GlobalDescr globalDescr : packageDescr.getGlobals()) {
            builder.append("global ")
                    .append(globalDescr.getType())
                    .append(" ")
                    .append(globalDescr.getIdentifier())
                    .append(";\n");
        }
        for (FunctionDescr functionDescr : packageDescr.getFunctions()) {
            builder.append(functionDescr).append("\n");
        }

        for (TypeDeclarationDescr typeDeclarationDescr :
                packageDescr.getTypeDeclarations()) {

        }

        for (RuleDescr ruleDescr : packageDescr.getRules()) {
            builder.append("rule ")
                    .append(ruleDescr.getName())
                    .append("\n");
            for (Map.Entry<String, AttributeDescr> entry : ruleDescr.getAttributes().entrySet()) {
                builder.append("\t")
                        .append(entry.getValue().getName())
                        .append(" ")
                        .append(entry.getValue().getValueString())
                        .append("\n");
            }
            builder.append("\twhen\n");
            AndDescr andDescr = ruleDescr.getLhs();
            for (BaseDescr baseDescr : andDescr.getDescrs()) {
                builder.append("\t\t")
                        .append(baseDescr)
                        .append("\n");
            }
            builder.append("\tthen\n");
            builder.append(ruleDescr.getConsequence()).append("\n");
            builder.append("end").append("\n");
        }

        log.info(builder.toString());
    }
}
