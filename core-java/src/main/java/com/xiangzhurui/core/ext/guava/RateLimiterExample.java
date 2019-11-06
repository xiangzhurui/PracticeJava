package com.xiangzhurui.core.ext.guava;

import com.google.common.util.concurrent.RateLimiter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author xiangzhurui
 * @version 2017/8/28
 */
@Slf4j
public class RateLimiterExample implements Runnable {
    RateLimiter rateLimiter;
    private int num;
    public RateLimiterExample(int num, RateLimiter rateLimiter) {
        this.num = num;
        this.rateLimiter = rateLimiter;
    }

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1);

        for (int i = 0; i < 1000; i++) {
            Thread thread = new Thread(new RateLimiterExample(i, rateLimiter));
            thread.start();
        }
    }

    @Override
    public void run() {
        rateLimiter.acquire();
        log.info("线程{}, Name={}; Id={}.", num, Thread.currentThread().getName(), Thread.currentThread().getId());
    }
}
