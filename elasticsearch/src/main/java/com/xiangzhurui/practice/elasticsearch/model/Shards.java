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
public class Shards {
    private Long total;
    private Long successful;
    private Long failed;
}
