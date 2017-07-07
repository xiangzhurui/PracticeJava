package design.patterns.singleton;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程安全，使用AtomicReference
 * Created by XiangZhuRui on 2017/7/7.
 */
public class Singleton5 {
    private AtomicReference<Boolean> isInit = new AtomicReference<>(false);
    private static volatile Singleton5 INSTANCE;

    private Singleton5() {
    }

    public Singleton5 getInstance() {
        if (isInit == null) {
            if (isInit.compareAndSet(false, true)) {
                INSTANCE = new Singleton5();
            }
        }

        return INSTANCE;
    }

}
