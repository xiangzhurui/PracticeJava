package me.xzr.practice.example.es.repo;

import com.google.gson.JsonObject;
import org.apache.http.Header;

import java.io.IOException;

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
    JsonObject get(String method, String endpoint, Header... headers);

    /**
     * 获取；搜索
     *
     * @return
     */
    JsonObject put(String method, String endpoint, Header... headers);

    /**
     * 删除
     *
     * @return
     */
    JsonObject delete(String method, String endpoint, Header... headers);

    /**
     * 更新
     *
     * @return
     */
    JsonObject post(String method, String endpoint, Header... headers);

    JsonObject head(String method, String endpoint, Header... headers);
    void close() throws IOException;
}
