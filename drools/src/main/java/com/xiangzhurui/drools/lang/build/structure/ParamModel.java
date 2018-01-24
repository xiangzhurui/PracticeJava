package com.xiangzhurui.drools.lang.build.structure;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

/**
 * 函数参数列表
 *
 * @author xiangzhurui
 * @version 2017/11/29
 */
@Getter
@EqualsAndHashCode
@Builder
public class ParamModel {

    private Integer index;
    private String type;
    private String name;
}
