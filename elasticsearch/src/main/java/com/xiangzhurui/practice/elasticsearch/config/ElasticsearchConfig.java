package com.xiangzhurui.practice.elasticsearch.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
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
    @Value("${es.host:127.0.0.1}")
    private String hosts;

    @Value("${es.port:9200}")
    private int port;

    @Value("${es.scheme:http}")
    private String scheme;

    @Value("${es.userName:elastic}")
    private String userName;

    @Value("${es.password:changeme}")
    private String password;

    public ElasticsearchConfig() {
    }

    /**
     * Elasticsearch 低级 REST 客户端
     *
     * @return client
     */
    @Bean(name = "lowLevelRestClient", destroyMethod = "close")
    public RestClient getLowLevelRestClient() {
        List<HttpHost> hostList = new ArrayList<>();
        String[] hostArray = hosts.split(","); //可用节点IP列表

        for (String hostname : hostArray) {
            if (StringUtils.isBlank(hostname)) {
                continue;
            }
            HttpHost httpHost = new HttpHost(hostname, port, scheme);
            hostList.add(httpHost);
        }

        //用户认证
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(userName, password));

        //默认HTTP请求头
        Header[] defaultHeaders = {new BasicHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_JSON.toString())};
        RestClient client = RestClient.builder(hostList.toArray(new HttpHost[hostList.size()]))
//                .setFailureListener(loggingFailureListener) //每次失败请求通知
                //.setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
//                    @Override
//                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
//                        return requestConfigBuilder.setConnectTimeout(5000)
//                                .setSocketTimeout(60000);
//                    }
//                })
                .setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
                    @Override
                    public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
                        return httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
                    }
                })
                .setDefaultHeaders(defaultHeaders) // 设置默认请求头
                .setMaxRetryTimeoutMillis(60000)  // 设置超时时间
                .build();

        return client;
    }

    /**
     * Elasticsearch 低级 REST 客户端
     *
     * @return client
     */
    @Bean(name = "highLevelClient")
    public RestHighLevelClient getHighLevelClient() {
        RestHighLevelClient client = new RestHighLevelClient(getLowLevelRestClient());
        return client;
    }

    /**
     * 节点嗅探。探测集群节点，并更新节点host列表。
     *
     * @return Sniffer
     */
    @Bean(destroyMethod = "close")
    public Sniffer getSniffer() {
        Sniffer sniffer = Sniffer.builder(getLowLevelRestClient())
                .setSniffIntervalMillis(30000)
                .setHostsSniffer(new ElasticsearchHostsSniffer(getLowLevelRestClient(), 6000, ElasticsearchHostsSniffer.Scheme.HTTP))
                .build();
        return sniffer;
    }
}
