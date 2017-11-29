package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import org.apache.commons.lang3.StringUtils;

/**
 * 规则
 * @author xiangzhurui
 * @version 2017/11/29
 */
@EqualsAndHashCode
@Builder
public class ScriptRule {
    @NonNull
    private String name;
    private ScriptRuleAttribute attribute;
    private String lhs;
    private String rhs;

    private String comment;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (StringUtils.isNotBlank(comment)) {
            builder.append("\n/* " + comment + " */ \n");
        }
        builder.append("rule \"" + name + "\"\n");
        if (StringUtils.isNotBlank(attribute.toString())) {
            builder.append(attribute.toString());
        }
        builder.append("    when \n")
                .append("        " + lhs + "\n")
                .append("    then \n")
                .append("        " + rhs + "\n")
                .append("end\n");
        return builder.toString();
    }
}
