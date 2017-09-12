package com.xiangzhurui.practice.elasticsearch.model;

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
public class MappingProperty<K, V> {
    private K k;
    private V v;
}
