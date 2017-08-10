package personal.xzr.practice.basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FinallyTest {
	final static Logger log = LoggerFactory.getLogger(FinallyTest.class);

	public static void main(String[] args) {
		FinallyTest t = new FinallyTest();
		t.isTest();

	}

	public boolean isTest(){
		try{
			log.info("before");
			return true;
		}finally{
			log.info("finally code");
		}
	}
}
