package com.xiangzhurui.drools.lang;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.drools.workbench.models.datamodel.rule.RuleAttribute;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiangzhurui
 * @version 2018/3/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RuleModel implements Serializable {
    private static final long serialVersionUID = 2656723767541331362L;

    private String packageName;
    private List<String> imports;
    private String ruleName;
    private RuleAttribute[] attributes;
    private String lhs;
    private String rhs;

    public void addAttribute(final RuleAttribute attribute) {

        final RuleAttribute[] list = this.attributes;
        final RuleAttribute[] newList = new RuleAttribute[list.length + 1];

        for (int i = 0; i < list.length; i++) {
            newList[i] = list[i];
        }
        newList[list.length] = attribute;

        this.attributes = newList;
    }
}
