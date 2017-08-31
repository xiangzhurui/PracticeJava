package com.xiangzhurui.practice.elasticsearch.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiangzhurui
 * @version 2017/8/31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PUTResponse {
    private String _index;
    private String _type;
    private String _id;
    private Long _version;
    private String result;
    private Shards _shards;

    private Boolean created;

}
