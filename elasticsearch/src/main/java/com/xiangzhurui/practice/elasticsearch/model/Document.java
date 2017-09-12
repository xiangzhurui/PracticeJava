package com.xiangzhurui.practice.elasticsearch.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiangzhurui
 * @version 2017/9/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Document {

    /**
     * 文档所在的索引
     */
    @JsonProperty("_index")
    private Index index;

    /**
     * 文档所在的类别
     */
    @JsonProperty("_type")
    private Type type;

    /**
     * 文档唯一标识,ID 是一个字符串，
     * 当它和 _index 以及 _type 组合就可以唯一确定 Elasticsearch 中的一个文档
     */
    @JsonProperty("_id")
    private String id;

    @JsonProperty("_uid")
    private String uid;
}
