package personal.xzr.practice.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author xiangzhurui
 * @version 2017/11/13
 */
@Slf4j
public class SystemClassTest {
    @Test
    public void test() {
        Properties systemProperties = System.getProperties();
        System.out.println("systemProperties:");
        for (Map.Entry entry : systemProperties.entrySet()) {
            System.out.println(entry.getKey() + "=" + entry.getValue());
        }
        String lineSeparator = System.getProperty("line.separator");
        String lineSeparator1 = System.getProperty("line.separator", "\n");
        System.out.println("lineSeparator+:/" + lineSeparator1 + "]");
        assertEquals("\n", lineSeparator);
        assertEquals("\n", lineSeparator1);

    }
}
