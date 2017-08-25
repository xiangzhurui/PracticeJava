package personal.xzr.practice.basic;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Test {

    public static void main(String[] args) throws InterruptedException {
        log.info("{}", args);
        if (args == null || args.length == 0) throw new AssertionError();
        log.info("{}", args);
    }

}
