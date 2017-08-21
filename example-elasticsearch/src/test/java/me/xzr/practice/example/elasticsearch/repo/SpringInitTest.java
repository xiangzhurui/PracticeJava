package me.xzr.practice.example.elasticsearch.repo;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import me.xzr.practice.example.elasticsearch.BaseSpringTest;
import me.xzr.practice.example.elasticsearch.SpringContextUtil;
import me.xzr.practice.example.elasticsearch.constant.HttpMethod;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import java.util.Map;
import java.util.Objects;

@Slf4j
public class SpringInitTest extends BaseSpringTest {
    @Autowired
    private SyncCurd syncCurd;

    @Test
    public void testGetApplicationContext() throws Exception {
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        Assert.assertNotNull(applicationContext);
    }
    @Test
    public void testGet() {
        log.info("======");
        JsonObject jsonObject = syncCurd.get(HttpMethod.GET, "/_search?pretty");
        if (Objects.nonNull(jsonObject)) {
            for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
                log.info(entry.getKey() + entry.getValue());
            }
        }
        Assert.assertNotNull(jsonObject);
    }

}
