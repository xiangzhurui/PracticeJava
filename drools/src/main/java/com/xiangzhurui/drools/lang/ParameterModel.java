package com.xiangzhurui.drools.lang;

import java.io.Serializable;

/**
 * @author xiangzhurui
 * @version 2018/3/19
 */
public class ParameterModel implements Serializable{
    private static final long serialVersionUID = -9113756826500971871L;

    private String typeName;
    private String argument;

    public ParameterModel(String typeName, String argument) {
        this.typeName = typeName;
        this.argument = argument;
    }

    public ParameterModel() {
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getArgument() {
        return argument;
    }

    public void setArgument(String argument) {
        this.argument = argument;
    }
}
