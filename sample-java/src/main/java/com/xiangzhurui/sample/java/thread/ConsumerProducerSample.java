package com.xiangzhurui.sample.java.thread;

import lombok.extern.slf4j.Slf4j;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * 生产者消费者使用 wait() 与 notifyAll() 线程通信示例
 * Created on 2017/7/14.
 * @author XiangZhuRui
 */
@Slf4j
public class ConsumerProducerSample {

    class Consumer extends Thread {
        private Queue<Integer> queue;
        private int maxSize;

        public Consumer(Queue<Integer> queue, int maxSize, String name) {
            super(name);
            this.maxSize = maxSize;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        System.out.println("Queue is empty Consumer thread is waiting");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                    System.out.println("消费值 : " + queue.remove());
                    queue.notifyAll();
                }
            }
        }
    }

    class Producer extends Thread {
        private Queue<Integer> queue;
        private int maxSize;

        public Producer(Queue<Integer> queue, int maxSize, String name) {
            super(name);
            this.maxSize = maxSize;
            this.queue = queue;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        System.out.println("Queue is full Producer is waiting");
                        try {
                            queue.wait();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int i = new Random().nextInt();
                    System.out.println("生产者添加数值为 : " + i);
                    queue.add(i);
                    queue.notifyAll();
                }
            }
        }
    }

    public static void main(String args[]) {
        Queue<Integer> queue = new LinkedList<>();
        int maxSize = 5;
        Thread consumer = new ConsumerProducerSample().new Consumer(queue, maxSize, "Consumer");
        Thread producer = new ConsumerProducerSample().new Producer(queue, maxSize, "Producer");
        log.info("start::::::");
        producer.start();
        consumer.start();

    }
}
