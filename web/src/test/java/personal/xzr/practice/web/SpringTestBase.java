package personal.xzr.practice.web;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import personal.xzr.practice.web.es.EsRestClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/rootContext.xml","classpath*:spring/webContext.xml"})
public class SpringTestBase {
}
