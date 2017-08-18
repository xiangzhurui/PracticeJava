package personal.xzr.practice.web.es;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class EsRestClient {
    @Value("es.hostname")
    private String hostname;
    @Value("es.port")
    private int port;
    @Value("es.scheme")
    private String scheme;

//    @Bean(name = "restClient", destroyMethod = "close")
    public RestClient getRestCleant() {
        RestClient restClient = RestClient.builder(new HttpHost(hostname, port, scheme)).build();
        return restClient;
    }
}
