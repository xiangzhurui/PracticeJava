package personal.xzr.practice.design.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程安全，使用AtomicReference
 * Created by XiangZhuRui on 2017/7/7.
 */
@Slf4j
public class Singleton5 {
    private static AtomicReference<Boolean> isInit = new AtomicReference<>(false);
    private static volatile Singleton5 INSTANCE;

    private Singleton5() {
        log.info("init===");
    }

    public static Singleton5 getInstance() {
        while (INSTANCE == null) {
            if (isInit.compareAndSet(false, true)) {
                INSTANCE = new Singleton5();
                break;
            }
        }

        return INSTANCE;
    }

    public void sayHello() {
        log.info("hello!");
    }
}
