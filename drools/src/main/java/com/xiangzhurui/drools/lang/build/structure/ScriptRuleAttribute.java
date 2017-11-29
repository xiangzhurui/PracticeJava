package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

/**
 * 规则属性
 *
 * @author xiangzhurui
 * @version 2017/11/29
 */
@EqualsAndHashCode
@Builder
public class ScriptRuleAttribute {


    /**
     * <code>no-loop</code>
     * <p>
     * 默认值: <code>false</code>
     * <p>
     * 数据类型: <code>Boolean</code>
     * <p>
     * When a rule’s consequence modifies a fact it may cause the rule to activate again, causing an infinite loop. Setting no-loop to true will skip the creation of another Activation for the rule with the current set of facts.
     */
    private Boolean noLoop;
    /**
     * ruleflow-group
     * 默认值 ： N/A
     * <p>
     * 类型 ：String
     * <p>
     * Ruleflow is a Drools feature that lets you exercise control over the firing of rules. Rules that are assembled by the same ruleflow-group identifier fire only when their group is active.
     */
    private String ruleflowGroup;
    /**
     * lock-on-active
     * 默认值 ： false
     * <p>
     * 类型 ：Boolean
     * <p>
     * Whenever a ruleflow-group becomes active or an agenda-group receives the focus, any rule within that group that has lock-on-active set to true will not be activated any more; irrespective of the origin of the update, the activation of a matching rule is discarded. This is a stronger version of no-loop, because the change could now be caused not only by the rule itself. It’s ideal for calculation rules where you have a number of rules that modify a fact and you don’t want any rule re-matching and firing again. Only when the ruleflow-group is no longer active or the agenda-group loses the focus those rules with lock-on-active set to true become eligible again for their activations to be placed onto the agenda.
     */
    private Boolean lockOnActive;
    /**
     * salience
     * 默认值 ： 0
     * <p>
     * 类型 ：integer
     * <p>
     * Each rule has an integer salience attribute which defaults to zero and can be negative or positive. Salience is a form of priority where rules with higher salience values are given higher priority when ordered in the Activation queue.
     * <p>
     * Drools also supports dynamic salience where you can use an expression involving bound variables.
     */
    private Integer salience;


    /**
     * agenda-group
     * 默认值 ： MAIN
     * <p>
     * 类型 ：String
     * <p>
     * Agenda groups allow the user to partition the Agenda providing more execution control. Only rules in the agenda group that has acquired the focus are allowed to fire.
     */
    private String agendaGroup;
    /**
     * auto-focus
     * 默认值 ： false
     * 类型 ：Boolean
     * <p>
     * When a rule is activated where the auto-focus value is true and the rule’s agenda group does not have focus yet, then it is given focus, allowing the rule to potentially fire.
     */
    private Boolean autoFocus;
    /**
     * activation-group
     * 默认值 ： N/A
     * <p>
     * 类型 ：String
     * <p>
     * Rules that belong to the same activation-group, identified by this attribute’s string value, will only fire exclusively. More precisely, the first rule in an activation-group to fire will cancel all pending activations of all rules in the group, i.e., stop them from firing.
     * <p>
     * Note: This used to be called Xor group, but technically it’s not quite an Xor. You may still hear people mention Xor group; just swap that term in your mind with activation-group.
     */
    private String activationGroup;

    /**
     * <code>dialect</code>
     * 默认值: as specified by the package
     * <p>
     * 类型: String
     * <p>
     * 可能的值: "java" 或 "mvel"
     * <p>
     * The dialect species the language to be used for any code expressions in the LHS or the RHS code block. Currently two dialects are available, Java and MVEL. While the dialect can be specified at the package level, this attribute allows the package definition to be overridden for a rule.
     * private String dialect;
     */
    private String dialect;

    /**
     * date-effective
     * 默认值 ： N/A
     * <p>
     * 类型 ：String, containing a date and time definition
     * <p>
     * A rule can only activate if the current date and time is after date-effective attribute.
     */
    private String dateEffective;
    /**
     * date-expires
     * 默认值 ： N/A
     * <p>
     * 类型 ：String, containing a date and time definition
     * <p>
     * A rule cannot activate if the current date and time is after the date-expires attribute.
     */
    private String dateExpires;
    /**
     * duration
     * 默认值 ： no default value
     * <p>
     * 类型 ：long
     * <p>
     * The duration dictates that the rule will fire after a specified duration, if it is still true.
     */
    private Long duration;

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        if (noLoop != null) {
            builder.append("    ")
                    .append("no-loop ").append(noLoop).append("\n")
            ;
        }
        if (ruleflowGroup != null) {
            builder.append("    ")
                    .append("ruleflow-group ").append(ruleflowGroup).append("\n")
            ;
        }
        if (lockOnActive != null) {
            builder.append("    ")
                    .append("no-loop ").append(lockOnActive).append("\n")
            ;
        }
        if (salience != null) {
            builder.append("    ")
                    .append("salience ").append(salience).append("\n")
            ;
        }
        if (agendaGroup != null) {
            builder.append("    ")
                    .append("agenda-group ").append(agendaGroup).append("\n")
            ;
        }
        if (autoFocus != null) {
            builder.append("    auto-focus ").append(autoFocus).append("\n")
            ;
        }
        if (StringUtils.isNotBlank(activationGroup)) {
            builder.append("    ")
                    .append("activation-group ").append(activationGroup).append("\n")
            ;
        }
        if (dialect != null) {
            builder.append("    ")
                    .append("dialect ").append(dialect).append("\n")
            ;
        }
        if (dateEffective != null) {
            builder.append("    ")
                    .append("date-effective ").append(dateEffective).append("\n")
            ;
        }
        if (dateExpires != null) {
            builder.append("    ")
                    .append("date-expires ").append(dateExpires).append("\n")
            ;
        }
        if (duration != null) {
            builder.append("    ")
                    .append("duration ").append(duration).append("\n")
            ;
        }
        return builder.toString();
    }
}
