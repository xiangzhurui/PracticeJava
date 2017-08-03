package basic.thread;

import org.junit.Assert;
import org.junit.Test;

public class AtomicityProblemTest {
    public static int sharedValue;

    //每次将sharedValue的值增加10
    public static void increment() {
        for (int i = 0; i < 10; i++) {
            sharedValue++;
        }
    }

//    @Test
    public static void main(String[] args)  throws InterruptedException {
        int maxThreads = 10000;
        for (int i = 0; i < maxThreads; i++) {
            Thread thread = new Thread(() -> increment());
            thread.start();
        }
        Thread.sleep(3000);//等待所有子线程执行完成
        System.out.println(sharedValue);
//        Assert.assertEquals(10000 * 10, sharedValue);
        //Exception in thread "main" java.lang.AssertionError: expected:<99980> but was:<100000>
    }

}
