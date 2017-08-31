package com.xiangzhurui.practice.elasticsearch.repo;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Collections;

/**
 * @author xiangzhurui
 * @version 2017/8/28
 */
@Slf4j
@Repository
public class BaseQueryDsl {

    @Autowired
    private RestClient restClient;

    /**
     * Query DSL 查询调用的一般方法
     *
     * @param endpoint
     * @param requestBodyString
     *
     * @return
     */
    public Response query(String method, String endpoint, String requestBodyString) {
        HttpEntity requestBody = new NStringEntity(requestBodyString, ContentType.APPLICATION_JSON);
        Response response = null;
        try {
            response = restClient.performRequest(method, endpoint, Collections.<String, String>emptyMap(), requestBody);
        } catch (IOException e) {
            log.error("", e);
        }
        return response;
    }

    /**
     * @param method
     * @param endpoint
     * @param requestBodyString
     *
     * @return
     */
    public HttpEntity queryAsHttpEntity(String method, String endpoint, String requestBodyString) {
        Response response = query(method, endpoint, requestBodyString);
        return response.getEntity();
    }

    /**
     * 异步 Query DSL 查询调用的一般方法
     *
     * @param endpoint
     * @param requestBodyString
     * @param responseListener
     */
    public void queryAsync(String method, String endpoint, String requestBodyString, ResponseListener responseListener) {
        HttpEntity requestBody = new NStringEntity(requestBodyString, ContentType.APPLICATION_JSON);
        restClient.performRequestAsync(method, endpoint, Collections.<String, String>emptyMap(), (ResponseListener) requestBody);
    }
}