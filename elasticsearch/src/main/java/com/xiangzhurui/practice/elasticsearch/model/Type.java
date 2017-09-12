package com.xiangzhurui.practice.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiangzhurui
 * @version 2017/9/12
 */
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Type {
    @JsonProperty("_index")
    private Index index;
    /**
     * 一个 _type 命名可以是大写或者小写，但是不能以下划线或者句号开头，不应该包含逗号， 并且长度限制为256个字符.
     */
    private String name;

}
