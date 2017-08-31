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
public class GetResponst<T> {
    private String _index;
    private String _type;
    private String _id;
    private String _version;
    private boolean found;
    private T _source;
}
