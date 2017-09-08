package com.xiangzhurui.practice.elasticsearch.repo;

import com.google.gson.JsonObject;

/**
 * @author xiangzhurui
 * @version 2017/9/5
 */
public interface QueryDsl {

    void query(String endpoint, String params, JsonObject requestBody);

}
