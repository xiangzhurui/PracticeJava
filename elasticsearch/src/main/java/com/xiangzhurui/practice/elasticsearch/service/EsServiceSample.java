package com.xiangzhurui.practice.elasticsearch.service;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.xiangzhurui.practice.elasticsearch.model.PutResponse;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Collections;

@Slf4j
@Service
public class EsServiceSample {
    @Autowired()
    private RestClient restClient;

    public boolean put() throws IOException {
        TestBean testBean = new TestBean("qbox", "Elasticsearch rest client");
        String testBeanStr = new Gson().toJson(testBean);
        HttpEntity entity = new NStringEntity(testBeanStr, ContentType.APPLICATION_JSON);
        //String method, String endpoint, Map<String, String> params, HttpEntity entity, Header...headers
        Response response = restClient.performRequest("PUT", "/blog/post/2", Collections.singletonMap("pretty", "true"), entity);
        HttpEntity responseEntity = response.getEntity();
        String result = EntityUtils.toString(responseEntity);
        log.info(result);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        log.info("{}", jsonObject.toString());
        PutResponse putResponse = new Gson().fromJson(result, PutResponse.class);
        log.info("putResponse对象：：：{}}", putResponse);
        return true;
    }

    public <T> T get(T t,String endpoint,String queryDslStr) throws IOException {
        HttpEntity entity = new NStringEntity(queryDslStr, ContentType.APPLICATION_JSON);
        Response response = restClient.performRequest("GET", endpoint, Collections.singletonMap("pretty", "true"), entity);
        HttpEntity responseEntity = response.getEntity();
        String result = EntityUtils.toString(responseEntity);
        log.info(result);
        JsonObject jsonObject = new JsonParser().parse(result).getAsJsonObject();
        log.info("{}", jsonObject.toString());
        return  null;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TestBean {
        private String company;
        private String title;
    }
}
