package design.patterns.singleton;

import static org.junit.Assert.*;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Slf4j
public class Singleton6Test {


    public static void main(String args[]) {
        //测试并发代码
        final CountDownLatch latch = new CountDownLatch(1);
        ExecutorService executorService = Executors.newFixedThreadPool(10);

        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                try {
                    latch.await();
                    //Singleton single = Singleton.INSTANCE;
                    Singleton6 single = Singleton6.getInstance();
                    log.info("{}线程single对象：{}",Thread.currentThread().getId(),single);
                    single.sayHello();
                } catch (InterruptedException e) {
                    log.error("",e);
                }
            });
        }

        latch.countDown();
    }
}
