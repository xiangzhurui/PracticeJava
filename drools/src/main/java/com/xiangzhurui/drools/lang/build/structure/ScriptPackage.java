package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

/**
 * @author xingzhurui
 * @version 2017/11/29
 */
@EqualsAndHashCode
@Builder
public class ScriptPackage {
    @NonNull
    private String pkg;

    @Override
    public String toString() {
        return "package " + pkg + ";\n";
    }
}
