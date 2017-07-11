package design.patterns.singleton;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 线程安全，使用AtomicReference,但会多次初始化对象（构造器会被多次调用）
 * Created by XiangZhuRui on 2017/7/11.
 */
@Slf4j
public class Singleton6 {

    private static AtomicReference<Singleton6> INSTANCE = new AtomicReference();

    private Singleton6() {
        log.info("init----{}", Thread.currentThread().getId());
    }

    public static Singleton6 getInstance() {
//        System.out.println("instance----"+INSTANCE.get()+",thread:"+Thread.currentThread());

        while (INSTANCE.get() == null) {
            INSTANCE.compareAndSet(null, new Singleton6());
        }
        return INSTANCE.get();
    }

    public void sayHello() {
        log.info("hello!");
    }
}
