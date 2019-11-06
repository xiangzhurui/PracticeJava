package com.xiangzhurui.core.ext.http;

import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基于 Apache HTTP client 的 http 连接池
 * @author xiangzhurui
 * @version 2018/9/4 11:23
 */
public class HttpConnectPool {
    private static final Logger log = LoggerFactory.getLogger(HttpConnectPool.class);

    private static class SingletonHolder {
        private static final HttpConnectPool HTTP_CONNECT_POOL = new HttpConnectPool();
    }

    public static HttpConnectPool getInstance() {
        return SingletonHolder.HTTP_CONNECT_POOL;
    }

    private HttpConnectPool() {
    }

    /**
     * @return
     * @since Apache HTTPClient 4.3
     */
    private HttpClientConnectionManager httpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager();

        poolingHttpClientConnectionManager.setMaxTotal(1000);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(1000);

        return poolingHttpClientConnectionManager;
    }

    private HttpClientBuilder httpClientBuilder() {
        HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();

        httpClientBuilder.setConnectionManager(httpClientConnectionManager());
        HttpRequestRetryHandler httpRequestRetryHandler = new DefaultHttpRequestRetryHandler(3, true);
        httpClientBuilder.setRetryHandler(httpRequestRetryHandler);

        return httpClientBuilder;
    }

    public HttpClient httpClient() {
        return httpClientBuilder().build();
    }

}
