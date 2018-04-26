package com.xiangzhurui.drools.engine.enums;

/**
 * 决策结果
 *
 * @author xiangzhurui
 * @version 2018/4/26
 */
public enum DecisionEnum {
    /**
     * 无风险，通过
     */
    ACCEPT,
    /**
     * 低风险，审查
     */
    REVIEW,
    /**
     * 高风险，拒绝
     */
    REJECT;
}
