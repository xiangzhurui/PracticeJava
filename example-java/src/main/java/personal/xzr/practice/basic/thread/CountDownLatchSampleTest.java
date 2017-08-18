package personal.xzr.practice.basic.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by XiangZhuRui on 2017/7/10.
 */
@Slf4j
public class CountDownLatchSampleTest {
    private static final int ThreadNum = 10;

    public static void main(String[] args) {

        //创建一个CountDownLatch实例，管理计数为ThreadNum
        CountDownLatch countDownLatch = new CountDownLatch(ThreadNum);

        //创建一个固定大小的线程池
        ExecutorService executor = Executors.newFixedThreadPool(ThreadNum);

        //添加线程到线程池
        for (int i = 0; i < ThreadNum; ++i) {
            executor.execute(new Person(countDownLatch, i + 1));
        }

        System.out.println("开始等待全员签到...");

        try {
            //等待所有线程执行完毕
            countDownLatch.await();
            System.out.println("签到完毕，开始吃饭");

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }

    }

    static class Person implements Runnable {

        private CountDownLatch countDownLatch;
        private int index;

        public Person(CountDownLatch cdl, int index) {
            this.countDownLatch = cdl;
            this.index = index;
        }

        @Override
        public void run() {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("person " + index + "签到");

            //线程执行完毕，计数器减一
            countDownLatch.countDown();

        }

    }


}
