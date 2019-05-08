package personal.xzr.practice.web.service.util;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import personal.xzr.practice.web.BaseSpringTest;

import static org.junit.Assert.assertNotNull;

@Slf4j
public class SpringContextUtilTest extends BaseSpringTest {

    @Test
    public void testGetApplicationContext() throws Exception {
        ApplicationContext applicationContext = SpringContextUtil.getApplicationContext();
        assertNotNull(applicationContext);
    }

    @Test
    public void testGetBean() {
        Object obj = SpringContextUtil.getBean("userController");
        log.info("============{}", obj);
        assertNotNull(obj);
    }
}
