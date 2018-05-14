package com.xiangzhurui.drools.engine.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 命中策略
 *
 * @author xiangzhurui
 * @version 2018/4/27
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HitPolicy implements Serializable {
    private static final long serialVersionUID = -6636876186948862424L;

    /**
     * 策略uuid
     */
    private String policyUuid;
    /**
     * 策略结果
     */
    private String policyDecision;
    /**
     * 策略模式
     */
    private String policyMode;
    /**
     * 策略名称
     */
    private String policyName;
    /**
     * 策略分数
     */
    private int policyScore;
    /**
     * 风险类型
     */
    private String riskType;
    /**
     * 命中规则列表
     */
    private List<HitRule> hitRules = new ArrayList();
}
