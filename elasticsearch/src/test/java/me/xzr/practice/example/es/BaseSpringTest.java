package me.xzr.practice.example.es;

import me.xzr.practice.example.es.config.ElasticsearchConfig;
import org.junit.runner.RunWith;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {ElasticsearchConfig.class})
public abstract class BaseSpringTest {
}
