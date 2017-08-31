package com.xiangzhurui.practice.elasticsearch.repo;

import com.google.gson.JsonObject;

/**
 * es 基本操作
 *
 * @author xiangzhurui
 * @version 2017.08.21
 */
public interface CRUD {
    /**
     * 创建；插入
     *
     * @return
     */
    JsonObject get(String method, String endpoint);

    /**
     * 获取；搜索
     *
     * @return
     */
    JsonObject put(String method, String endpoint);

    /**
     * 删除
     *
     * @return
     */
    JsonObject delete(String method, String endpoint);

    /**
     * 更新
     *
     * @return
     */
    JsonObject post(String method, String endpoint);

    JsonObject head(String method, String endpoint);
}
