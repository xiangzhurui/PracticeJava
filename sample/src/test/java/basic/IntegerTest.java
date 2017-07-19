package basic;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Integer 特性
 * @author XiangZhuRui
 */
public class IntegerTest {

    /**
     * Integer 对象在值为-128~127 之间时使用内部的IntegerCache 缓存。
     * @author XiangZhuRui
     */
    @Test
    public void testIntegerCache() {
        Integer a = 12;
        Integer b = 12;
        Integer c = 128;
        Integer d = 128;
        Integer e = -128;
        Integer f = -128;
        assertEquals(true, a == b);
        assertEquals(true, e == f);
        assertEquals(false, c == d);
    }

}
