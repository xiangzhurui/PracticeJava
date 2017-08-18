package me.xzr.practice.example.elasticsearch.me.xzr.practice.example.elasticsearch.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;

public class EsConfig {

    String host;
    int port;

    public RestClient getRestClient() {
        HttpHost httpHost0 = new HttpHost("localhost", 9200, "http");
        HttpHost httpHost1 = new HttpHost("localhost", 9201, "http");

//        RestClient restClient = RestClient.builder(new HttpHost("localhost", 9200))
//                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//                    @Override
//                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//                        return requestConfigBuilder.setConnectTimeout(5000)
//                                .setSocketTimeout(60000);
//                    }
//                })
//                .setMaxRetryTimeoutMillis(60000)
//                .build();

        return RestClient.builder(httpHost0, httpHost1).build();
    }

    public HttpHost getHttpHost(String hostname, int port, String scheme) {
        return new HttpHost(hostname, port, scheme);
    }
}
