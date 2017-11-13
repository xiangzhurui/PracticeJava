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
        if("windows".equalsIgnoreCase(System.getProperty("sun.desktop"))){
            log.info("当前处在 Windows 平台");
            assertEquals("\r\n", lineSeparator);
        }else{
            log.info("当前处在非 Windows 平台");
            assertEquals("\n", lineSeparator);
        }

    }
}
