package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 全局变量
 * @author xiangzhurui
 * @version 2017/11/29
 */
@EqualsAndHashCode
@Builder
public class ScriptGlobal {

    private String name;
    private String type;

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    @Override
    public String toString() {
        return "global " + (StringUtils.isNotBlank(this.type) ? this.type : "") + " " + this.name + ";\n";
    }
}
