package com.xiangzhurui.practice.elasticsearch.repo;

import com.google.gson.JsonObject;
import com.xiangzhurui.practice.elasticsearch.constant.HttpMethod;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xiangzhurui
 * @version 2017/9/5
 */
@Repository
public class IndexSample {
    @Autowired
    RestClient restClient;

    public Map insert(String index, String type, String id, JsonObject jsonObject) throws IOException {
        Map resultMap = new HashMap();
        Map params = Collections.emptyMap();
        restClient.performRequest(HttpMethod.POST, "", params, null);

        resultMap.put("200", "保存ES成功!");
        return resultMap;
    }

    public void deleteType(String type){

    }


}
