package com.xiangzhurui.practice.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * @author xiangzhurui
 * @version 2017/9/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mapping {
    private String name;
    @JsonProperty("_type")
    private Type type;
    private Map<String, Mapping> properties;
}
