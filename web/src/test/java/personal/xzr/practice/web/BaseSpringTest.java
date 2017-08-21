package personal.xzr.practice.web;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/rootContext.xml", "classpath*:spring/webContext.xml"})
@WebAppConfiguration
public class BaseSpringTest {
}
