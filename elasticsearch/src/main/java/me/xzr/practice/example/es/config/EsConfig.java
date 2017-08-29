package me.xzr.practice.example.es.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.sniff.ElasticsearchHostsSniffer;
import org.elasticsearch.client.sniff.HostsSniffer;
import org.elasticsearch.client.sniff.Sniffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Elasticsearch 配置
 */
@Configuration
public class EsConfig {
    @Value("es.host")
    private String host;

    @Value("es.port")
    private String port;

    @Value("es.scheme")
    private String scheme;

    private HttpHost[] hostList;
    private Sniffer sniffer;


    public EsConfig() {
    }


    @Bean(name = "esRestClient", destroyMethod = "close")
    public RestClient getRestClient() {

        RestClient client = RestClient.builder(hostList)
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

    @Bean(destroyMethod = "close")
    public Sniffer getSniffer() {
        this.sniffer = Sniffer.builder(getRestClient())
                .setHostsSniffer(new ElasticsearchHostsSniffer(getRestClient(),6000,ElasticsearchHostsSniffer.Scheme.HTTP))
                .build();
        return sniffer;
    }
}
