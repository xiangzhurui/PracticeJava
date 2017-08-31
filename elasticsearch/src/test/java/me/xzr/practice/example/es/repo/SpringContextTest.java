package me.xzr.practice.example.es.repo;

import lombok.extern.slf4j.Slf4j;
import me.xzr.practice.example.es.config.BaseSpringTest;
import me.xzr.practice.example.es.util.SpringContextUtil;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;

/**
 * 测试 Spring context 初始化是否成功。
 */
@Slf4j
public class SpringContextTest extends BaseSpringTest {

    @Test
    public void testGetApplicationContext() throws Exception {
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        Assert.assertNotNull(applicationContext);
    }

}
