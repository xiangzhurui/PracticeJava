package com.xiangzhurui.util.drools.enums;

/**
 * 连接运算符
 *
 * @author xiangzhurui
 * @version 2018/8/13 15:27
 */
public enum ConnectionOperatorEnum {
    AND("&&", "和"),
    OR("||", "或");

    private String code;
    private String name;

    ConnectionOperatorEnum(String code, String name) {
        this.code = code;
        this.name = name;
    }
}
