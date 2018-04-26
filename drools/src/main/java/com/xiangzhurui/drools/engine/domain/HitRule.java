package com.xiangzhurui.drools.engine.domain;

import com.xiangzhurui.drools.engine.enums.DecisionEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 命中规则
 *
 * @author xiangzhurui
 * @version 2018/4/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HitRule implements Serializable {
    private String id;
    private String name;
    private DecisionEnum decision;
}
