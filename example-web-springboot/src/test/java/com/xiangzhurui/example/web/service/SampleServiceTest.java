package com.xiangzhurui.example.web.service;

import com.xiangzhurui.example.web.ExampleWebApplicationTests;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.TimeUnit;
@Slf4j
public class SampleServiceTest extends ExampleWebApplicationTests {

    @Autowired
    private SampleService sampleService;

    @Test
    public void asyncMethod() {

        for (int i = 0; i < 10; i++) {
            sampleService.asyncMethod(i);
        }
        log.info("start test sleep");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            log.info("eee",e);
        }
        log.info("test sheep end");
    }

}