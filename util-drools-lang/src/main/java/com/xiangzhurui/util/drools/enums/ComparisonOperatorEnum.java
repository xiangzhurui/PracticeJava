package com.xiangzhurui.util.drools.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * 比较运算符
 *
 * @author xiangzhurui
 * @version 2018/8/13 15:08
 */
public enum ComparisonOperatorEnum {

    GREATER_THAN(">", "大于"),
    GREATER_OR_EQUAL_THAN(">=", "大于等于"),
    LESS_THAN("<", "小于"),
    LESS_OR_EQUAL_THAN("<", "小于等于"),
    EQUALS("=", "等于"),
    NOT_EQUALS("!=", "不等于"),
    MATCHES("matches", "匹配"),
    NOT_MATCHES("not matches", "不匹配"),
    CONTAINS("contains", "包含"),
    NOT_CONTAINS("not contains", "不包含");

    private String code;
    private String name;

    ComparisonOperatorEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }

    private static final Map<String, ComparisonOperatorEnum> mappings = new HashMap<>(8);

    static {
        for (ComparisonOperatorEnum opratorChars : values()) {
            mappings.put(opratorChars.name(), opratorChars);
        }
    }
}
