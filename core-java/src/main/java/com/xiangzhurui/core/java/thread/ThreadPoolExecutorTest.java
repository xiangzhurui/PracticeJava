package com.xiangzhurui.core.java.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by XiangZhuRui on 2017/7/12.
 */
public class ThreadPoolExecutorTest {
    public static class MyThread extends Thread {
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {

                System.out.println("do Someing...."+System.currentTimeMillis());

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {

        MyThread t = new MyThread();
        t.start();
        System.out.println("do 111...."+System.currentTimeMillis());
        try {
            TimeUnit.MICROSECONDS.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("do 222...."+System.currentTimeMillis());

        t.interrupt();
        System.out.println("do 333...."+System.currentTimeMillis());

    }


}
