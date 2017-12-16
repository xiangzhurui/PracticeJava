package com.xiangzhurui.example.webapp.service.impl;

import com.xiangzhurui.example.webapp.service.SampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author xiangzhurui
 * @version 2017/12/16
 */
@Slf4j
@Service
public class SampleServiceImpl implements SampleService {
    @Override
    public void asyncMethod(Integer i) {
        log.info("i:[{}]开始", i);
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            log.error("[{}]错误：", i, e);
        } finally {
            log.info("i:[{}]结束", i);
        }

    }
}
