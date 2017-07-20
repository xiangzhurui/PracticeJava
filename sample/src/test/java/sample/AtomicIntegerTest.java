package sample;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicIntegerTest {
    @Test
    public void testIncrementAndGet() {
        AtomicInteger atom = new AtomicInteger();
        Assert.assertEquals(0, atom.get());
        Assert.assertEquals(1, atom.incrementAndGet());
        Assert.assertEquals(2, atom.incrementAndGet());
        Assert.assertEquals(3, atom.incrementAndGet());
        Assert.assertEquals(4, atom.incrementAndGet());
        Assert.assertEquals(5, atom.incrementAndGet());
    }
}
