package com.xiangzhurui.sample.java.thread;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class TreadTest {
    static boolean b = true;
    public static void main(String[] args) {
        System.out.println("start" + Thread.currentThread());

        new Thread(() -> {
            System.out.println("等待线程" + Thread.currentThread());
            while (b) {
            }
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("end" + Thread.currentThread());
        System.out.println();
        b = false;
    }

    public static void testStart() {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interruted!" + new Date());
                        break;
                    }
                    Thread.yield();
                }
            }
        };
        thread.start();
        thread.interrupt();
    }

    public static void testRun() {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                while (true) {
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Interruted!");
                        break;
                    }
                    Thread.yield();
                }
            }
        };
        thread.run();
    }
}
