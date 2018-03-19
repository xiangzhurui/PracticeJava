package com.xiangzhurui.drools.lang;

import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * @author xiangzhurui
 * @version 2018/3/19
 */
@Data
public class FunctionModel implements Serializable {
    private static final long serialVersionUID = 6056924996071537962L;

    private static final String KEYWORD = "function";

    private String packageName;
    private String functionName;
    private String returnType;
    private ParameterModel[] parameterModels;
    private String functionContent;

    public void addParameter(ParameterModel parameterModel) {

        final ParameterModel[] list = this.parameterModels;
        final ParameterModel[] newList = new ParameterModel[list.length + 1];

        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        newList[list.length] = parameterModel;

        this.parameterModels = newList;

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(KEYWORD).append(" ");
        if (returnType != null && returnType.trim() != "") {
            builder.append(returnType).append(" ");
        }
        if (functionName == null && functionName.trim() == "") {
            throw new IllegalStateException("not had function name:[" + functionName + "]!");
        }
        builder.append(functionName).append(" ").append("(");

        if (parameterModels != null && parameterModels.length != 0) {
            for (ParameterModel parameterModel : parameterModels) {
                builder.append(parameterModel.getTypeName())
                        .append(" ")
                        .append(parameterModel.getArgument());
            }
        }
        builder.append(")");
        builder.append("{");
        if (StringUtils.isNotBlank(functionContent)) {
            builder.append(functionContent);
        }
        builder.append("}");
        return builder.toString();
    }
}
