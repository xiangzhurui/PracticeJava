package com.xiangzhurui.drools.engine.enums;

import lombok.Getter;

/**
 * 策略模式
 *
 * @author xiangzhurui
 * @version 2018/4/26
 */
@Getter
public enum PolicyModeEnum {
    WORST_MATCH("WorstMatch", "最坏匹配模式"),
    WEIGHTED("Weighted","权重模式");

    private String code;
    private String desc;

    PolicyModeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

}
