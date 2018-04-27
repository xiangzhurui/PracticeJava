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
 * 风险详情结果响应
 * @author xiangzhurui
 * @version 2018/4/27
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RiskResponse implements Serializable {
    private static final long serialVersionUID = 2888963606195754766L;

    /**
     * 执行是否成功，不成功时对应reason_code
     */
    private Boolean success = false;
    /**
     * 错误码及原因描述，正常执行完扫描时为空
     */
    private String reasonCode;
    /**
     * 风险分数
     */
    private Integer finalScore;
    /**
     * 决策结果
     */
    private String finalDecision;

    private List<HitPolicy> policySet = new ArrayList<>();
}
