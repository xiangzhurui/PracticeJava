package design.patterns.singleton;

/**
 * 饥汉式，线程安全，可以被反射破坏
 * Created by XiangZhuRui on 2017/7/7.
 */
public class Singleton3 {
    private final static Singleton3 INSTANCE = new Singleton3();

    private Singleton3() {
    }

    public static Singleton3 getInstance(){
        return INSTANCE;
    }
}
