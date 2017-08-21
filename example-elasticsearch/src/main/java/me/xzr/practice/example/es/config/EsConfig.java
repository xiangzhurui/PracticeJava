package me.xzr.practice.example.es.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

/**
 * 暂不使用
 */
//@Configuration
@Deprecated
public class EsConfig {
    @Value("es.host")
    private String host;

    @Value("es.port")
    private String port;

    @Value("es.scheme")
    private String scheme;

    private HttpHost[] hostList;

    private RestClient restClient;

    public EsConfig() {
    }


//    @Bean
    public RestClient getRestClient() {
        return RestClient.builder(hostList).build();
    }

    //@Bean
    public RestClient getRestClient1() {

        restClient = RestClient.builder(hostList)
                .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
                    @Override
                    public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
                        return requestConfigBuilder.setConnectTimeout(5000)
                                .setSocketTimeout(60000);
                    }
                })
                .setMaxRetryTimeoutMillis(60000)
                .build();

        return restClient;
    }

    public void close() throws IOException {
        restClient.close();
    }

    public void setHostList(HttpHost[] hostList) {
        this.hostList = hostList;
    }
}
