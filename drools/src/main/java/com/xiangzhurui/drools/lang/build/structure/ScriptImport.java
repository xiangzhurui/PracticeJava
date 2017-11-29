package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;

/**
 * 引入
 * @author xiangzhurui
 * @version 2017/11/29
 */
@EqualsAndHashCode
@Builder
public class ScriptImport {
    private Class target;

    @Override
    public String toString() {
        return "import "+target.getName() +";\n";
    }
}
