package com.xiangzhurui.practice.elasticsearch.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.entity.ContentType;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.sniff.ElasticsearchHostsSniffer;
import org.elasticsearch.client.sniff.Sniffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;

import java.util.ArrayList;
import java.util.List;


/**
 * Elasticsearch 配置
 */
@Configuration
@PropertySource(value = "classpath:es.properties")
@ComponentScan(basePackages = {"com.xiangzhurui.practice.elasticsearch"})
@ComponentScans(value = {})
public class ElasticsearchConfig {
    @Value("${es.host}")
    private String hosts;

    @Value("${es.port}")
    private int port;

    @Value("${es.scheme}")
    private String scheme;


    public ElasticsearchConfig() {
    }

    /**
     * Elasticsearch 客户端
     *
     * @return RestClient
     */
    @Bean(name = "esRestClient", destroyMethod = "close")
    public RestClient getRestClient() {
        List<HttpHost> hostList = new ArrayList<>();
        String[] hostArray = hosts.split(",");

        for (String hostname : hostArray) {
            if (StringUtils.isBlank(hostname)) {
                continue;
            }
            HttpHost httpHost = new HttpHost(hostname, port, scheme);
            hostList.add(httpHost);
        }
        Header[] defaultHeaders = { new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())};
        RestClient client = RestClient.builder(hostList.toArray(new HttpHost[hostList.size()]))
//                .setFailureListener(loggingFailureListener) //每次失败请求通知
                //.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//                    @Override
//                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//                        return requestConfigBuilder.setConnectTimeout(5000)
//                                .setSocketTimeout(60000);
//                    }
//                })
                .setDefaultHeaders(defaultHeaders) // 设置默认请求头
                .setMaxRetryTimeoutMillis(60000)  // 设置超时时间
                .build();

        return client;
    }

    /**
     * 节点嗅探。探测集群节点，并更新节点host列表。
     *
     * @return Sniffer
     */
    @Bean(destroyMethod = "close")
    public Sniffer getSniffer() {
        Sniffer sniffer = Sniffer.builder(getRestClient())
                .setSniffIntervalMillis(30000)
                .setHostsSniffer(new ElasticsearchHostsSniffer(getRestClient(), 6000, ElasticsearchHostsSniffer.Scheme.HTTP))
                .build();
        return sniffer;
    }
}
