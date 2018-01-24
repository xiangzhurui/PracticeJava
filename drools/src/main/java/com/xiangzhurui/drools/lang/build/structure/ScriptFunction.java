package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * 函数
 * @author xiangzhurui
 * @version 2017/11/29
 */
@EqualsAndHashCode
@Builder
public class ScriptFunction {

    @NonNull
    private String name;
    private String returnType;
    private ParamModel param;
    private String content;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("function ")
                .append(returnType == null ? "void" : returnType)
                .append(" ").append(name)
                .append("(").append(param.toString()).append("){\n")
                .append(content).append("\n")
                .append("}\n");

        return builder.toString();
    }


}
