package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 函数参数列表
 *
 * @author xiangzhurui
 * @version 2017/11/29
 */
@EqualsAndHashCode
@Builder
public class ScriptFunctionParam {

    private Integer index;
    private String type;
    private String name;

    private List<ScriptFunctionParamBuilder> paramList;

    @Override
    public String toString() {
        Collections.sort(paramList, new Comparator<ScriptFunctionParamBuilder>() {
            @Override
            public int compare(ScriptFunctionParamBuilder o, ScriptFunctionParamBuilder t1) {
                return o.index.compareTo(t1.index);
            }
        });
        StringBuilder builder = new StringBuilder();
        for (ScriptFunctionParamBuilder param : paramList) {
            builder.append(param.type).append(" ").append(param.name).append(", ");
        }
        return builder.toString();
    }

}
