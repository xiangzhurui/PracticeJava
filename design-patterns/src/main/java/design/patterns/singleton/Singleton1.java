package design.patterns.singleton;

/**
 * 懒汉式，线程安全，性能问题
 * Created by XiangZhuRui on 2017/7/7.
 */
public class Singleton1 {
    private static Singleton1 single;

    private Singleton1() {
    }

    public static synchronized Singleton1 getInstance() {
        if (single == null) {
            single = new Singleton1();
        }
        return single;
    }
}
