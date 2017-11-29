package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.util.Collections;
import java.util.Map;

/**
 * 类型生命
 * @author xiangzhurui
 * @version 2017/11/29
 */
@EqualsAndHashCode
@Builder
public class ScriptDeclareType {
    @NonNull
    private String name;
    /**
     * name:type
     */
    @NonNull
    private Map<String, String> types = Collections.EMPTY_MAP;

    public Map<String, String> getTypes() {
        return types;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("declare ").append(name).append("\n");

        if (!types.isEmpty()) {
            for (Map.Entry<String, String> entry : types.entrySet()) {
                builder.append("    ");
                builder.append(entry.getKey()).append(" : ").append(entry.getValue()).append("\n");
            }
        }
        builder.append("end\n");
        return builder.toString();
    }
}
