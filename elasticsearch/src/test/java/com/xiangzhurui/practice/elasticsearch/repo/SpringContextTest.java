package com.xiangzhurui.practice.elasticsearch.repo;

import lombok.extern.slf4j.Slf4j;
import com.xiangzhurui.practice.elasticsearch.config.BaseSpringTest;
import com.xiangzhurui.practice.elasticsearch.util.SpringContextUtil;
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
