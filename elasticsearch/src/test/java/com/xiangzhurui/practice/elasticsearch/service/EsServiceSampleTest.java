package com.xiangzhurui.practice.elasticsearch.service;

import com.xiangzhurui.practice.elasticsearch.EsServiceSample;
import com.xiangzhurui.practice.elasticsearch.config.BaseSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class EsServiceSampleTest extends BaseSpringTest {
    @Autowired
    private EsServiceSample esServiceSample;
    @Test
    public void testSave() {
        try {
            esServiceSample.put();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGet() throws IOException {
        String queryDslStr="";
        esServiceSample.get(EsServiceSample.TestBean.class,"/blog/post/2",queryDslStr);
    }
}