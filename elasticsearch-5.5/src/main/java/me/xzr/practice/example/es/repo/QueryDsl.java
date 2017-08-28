package me.xzr.practice.example.es.repo;

import lombok.extern.slf4j.Slf4j;
import me.xzr.practice.example.es.constant.HttpMethod;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;

/**
 * @author xiangzhurui
 * @version 2017/8/28
 */
@Slf4j
public class QueryDsl {

    @Autowired
    private RestClient restClient;

    /**
     * Query DSL 查询调用的一般方法
     *
     * @param url
     * @param requestBodyString
     *
     * @return
     */
    public Response query(String url, String requestBodyString) {
        HttpEntity requestBody = new NStringEntity(requestBodyString, ContentType.APPLICATION_JSON);
        Response response = null;
        try {
            response = restClient.performRequest(HttpMethod.POST, url, Collections.<String, String>emptyMap(), requestBody);
        } catch (IOException e) {
            log.error("", e);
        }
        return response;
    }

    /**
     * 异步 Query DSL 查询调用的一般方法
     *
     * @param url
     * @param requestBodyString
     * @param responseListener
     */
    public void queryAsync(String url, String requestBodyString, ResponseListener responseListener) {
        HttpEntity requestBody = new NStringEntity(requestBodyString, ContentType.APPLICATION_JSON);
        restClient.performRequestAsync(HttpMethod.POST, url, Collections.<String, String>emptyMap(), (ResponseListener) requestBody);
    }
}
