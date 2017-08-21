package me.xzr.practice.example.elasticsearch.repo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

@Slf4j
@Component
public class SyncCurd implements CRUD {

    @Autowired
    private RestClient restClient;

    public SyncCurd() {
    }

    @Override
    public JsonObject get(String method, String endpoint, Header... headers) {
        try {
            Response response = restClient.performRequest(method, endpoint, headers);
            HttpEntity httpEntity = response.getEntity();
            InputStream inputStream = httpEntity.getContent();
            String theString = IOUtils.toString(inputStream, "UTF-8");
            log.debug("GET响应的字符串{}",theString);
            JsonObject jsonObject = new JsonParser().parse(theString).getAsJsonObject();

            return jsonObject;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public JsonObject put(String method, String endpoint, Header... headers) {

        return null;
    }

    @Override
    public JsonObject delete(String method, String endpoint, Header... headers) {
        return null;
    }

    @Override
    public JsonObject post(String method, String endpoint, Header... headers) {
        return null;
    }

    @Override
    public JsonObject head(String method, String endpoint, Header... headers) {
        return null;
    }

    @Override
    public void close() throws IOException {
        restClient.close();
    }
}
