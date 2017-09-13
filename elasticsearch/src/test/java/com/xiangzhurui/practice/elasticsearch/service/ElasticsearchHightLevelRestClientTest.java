package com.xiangzhurui.practice.elasticsearch.service;

import com.xiangzhurui.practice.elasticsearch.config.BaseSpringTest;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertNotNull;

/**
 * ES REST 高级客户端测试
 *
 * @author xiangzhurui
 * @version 2017/9/13
 */
@Slf4j
public class ElasticsearchHightLevelRestClientTest extends BaseSpringTest {

    @Autowired
    private RestHighLevelClient client;

    @Test
    public void test() {

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("user", "kimchy");
        jsonMap.put("postDate", new Date());
        jsonMap.put("message", "trying out Elasticsearch");
        IndexRequest indexRequest = new IndexRequest("posts", "doc", "1")
                .source(jsonMap);
        IndexResponse indexResponse = null;
        try {
            indexResponse = client.index(indexRequest);
        } catch (IOException e) {
            log.error("索引发生错误：", e);
        }
        log.info("{}", indexResponse);
        assertNotNull(indexResponse);
    }
}
