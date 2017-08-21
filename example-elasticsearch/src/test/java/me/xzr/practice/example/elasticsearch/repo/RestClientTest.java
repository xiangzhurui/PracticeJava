package me.xzr.practice.example.elasticsearch.repo;

import lombok.extern.slf4j.Slf4j;
import me.xzr.practice.example.elasticsearch.BaseSpringTest;
import org.apache.http.HttpEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.nio.entity.NStringEntity;
import org.apache.http.util.EntityUtils;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.ResponseListener;
import org.elasticsearch.client.RestClient;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;

@Slf4j
public class RestClientTest extends BaseSpringTest {

    @Autowired
    private RestClient restClient;


    @Test
    public void testPerformRequest() throws IOException {
//        Object obj =SpringContextUtil.getBean("httpHost");
//        log.info("{}",obj);
        Response response = restClient.performRequest("GET", "/",
                Collections.singletonMap("pretty", "true"));
        log.info(EntityUtils.toString(response.getEntity()));

        //index a document
        HttpEntity entity = new NStringEntity(
                "{\n" +
                        "    \"user\" : \"kimchy\",\n" +
                        "    \"post_date\" : \"2009-11-15T14:12:12\",\n" +
                        "    \"message\" : \"trying out Elasticsearch\"\n" +
                        "}", ContentType.APPLICATION_JSON);

        Response indexResponse = restClient.performRequest("PUT",
                "/twitter/tweet/1",
                Collections.<String, String>emptyMap(),
                entity);
    }

    public void test2() throws InterruptedException {
        int numRequests = 10;
        final CountDownLatch latch = new CountDownLatch(numRequests);

        for (int i = 0; i < numRequests; i++) {
            restClient.performRequestAsync(
                    "PUT",
                    "/twitter/tweet/" + i,
                    Collections.<String, String>emptyMap(),
                    //assume that the documents are stored in an entities array entities[i],
                    new ResponseListener() {
                        @Override
                        public void onSuccess(Response response) {
                            System.out.println(response);
                            latch.countDown();
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            latch.countDown();
                        }
                    }
            );
        }

        //wait for all requests to be completed
        latch.await();
    }
}
