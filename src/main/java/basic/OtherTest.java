package basic;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OtherTest {
	private static final  Logger log = LoggerFactory.getLogger(OtherTest.class);
	public static void main(String[] args) {
		  log.error("error");
	      log.debug("debug");
	      log.info("info");
	      log.trace("trace");
	      log.warn("warn");
	      log.error("error {}", "param");
	}
}
