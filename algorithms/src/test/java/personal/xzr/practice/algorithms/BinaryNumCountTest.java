package personal.xzr.practice.algorithms;

import org.junit.Assert;
import org.junit.Test;

public class BinaryNumCountTest {
    @Test
    public void testCount0() {
        int result = BinaryNumCount.count0((byte) 125);
        System.out.println(result);
        Assert.assertEquals(6, result);
    }

    @Test
    public void test() {
        int a = 1;

        System.out.println(a >>>= 1);
    }
}
