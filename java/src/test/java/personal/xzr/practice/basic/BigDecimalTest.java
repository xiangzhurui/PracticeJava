package personal.xzr.practice.basic;

import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * BigDecimal 使用
 *
 * @author xiangzhurui
 * @version 2017/9/13
 */
@Slf4j
public class BigDecimalTest {

    @Test
    public void testPow() {
        BigDecimal bigDecimal = BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(10).pow(5));
        Assert.assertEquals(200000, bigDecimal.intValue());
    }

    @Test
    public void testConsturct() {
        log.info("测试new BigDecimal(0.8)与BigDecimal.valueOf(0.8) 不相等");
        BigDecimal bigDecimal = new BigDecimal(0.8);
        BigDecimal bigDecimal1 = BigDecimal.valueOf(0.8);
        BigDecimal bigDecimal2 = new BigDecimal("0.8");

        log.info("new BigDecimal(0.8)的值是：{}", bigDecimal);
        log.info("BigDecimal.valueOf(0.8)的值是：{}", bigDecimal1);
        log.info("bigDecimal与bigDecimal1是否相等：{}", bigDecimal.equals(bigDecimal1) ? "相等" : "不相等");

        log.info("new BigDecimal(\"0.8\")的值是：{}", bigDecimal2);
        assertNotEquals(bigDecimal, bigDecimal1);
        assertEquals(bigDecimal1, bigDecimal2);
    }
}
