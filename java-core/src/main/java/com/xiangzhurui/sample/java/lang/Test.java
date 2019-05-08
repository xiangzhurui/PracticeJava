package com.xiangzhurui.sample.java.lang;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Test {
	static final Logger log = LoggerFactory.getLogger(Test.class);

	public static void main(String[] args) {
		int i = Integer.MAX_VALUE;
		System.out.println(i);
		log.info("{}",i);
		log.info("{}",i*+i-1);
		log.info("{}",(i<<2)+4);
		log.info("{}", 1<<16);
	}

}
