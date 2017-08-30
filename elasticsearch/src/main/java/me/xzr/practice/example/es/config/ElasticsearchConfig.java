package me.xzr.practice.example.es.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpHost;
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
@ComponentScan(basePackages = {"me.xzr.practice.example.es"})
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

        RestClient client = RestClient.builder(hostList.toArray(new HttpHost[hostList.size()]))
//                .setFailureListener(loggingFailureListener)
                //.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//                    @Override
//                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//                        return requestConfigBuilder.setConnectTimeout(5000)
//                                .setSocketTimeout(60000);
//                    }
//                })
                .setMaxRetryTimeoutMillis(60000)
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
