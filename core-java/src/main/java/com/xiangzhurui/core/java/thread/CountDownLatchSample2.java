package com.xiangzhurui.core.java.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.time.StopWatch;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CountDownLatchSample2 {

	public static void main(String[] args) throws InterruptedException {
		StopWatch stopWatch = new StopWatch();
		stopWatch.start();
		log.info("开始:{}",stopWatch.getTime());
		CountDownLatch latch = new CountDownLatch(1);
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(new WaitTask(latch,stopWatch));
		log.info("1:[{}]",stopWatch.getTime()/1000);

		latch.await(2, TimeUnit.SECONDS);
		log.info("2:[{}]",stopWatch.getTime()/1000);
		executorService.shutdown();
	}

	public static class WaitTask implements Runnable {
		CountDownLatch latch;
		StopWatch stopWatch;
		public WaitTask(CountDownLatch lantch,StopWatch stopWatch) {
			this.latch = lantch;
			this.stopWatch=stopWatch;
		}

		@Override
		public void run() {
			try {
				TimeUnit.SECONDS.sleep(5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.latch.countDown();
			log.info("2:[{}]",stopWatch.getTime()/1000);
			
		}
	}

}
