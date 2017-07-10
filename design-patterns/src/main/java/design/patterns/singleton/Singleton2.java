package design.patterns.singleton;

/**
 * 懒汉式，线程安全;
 * 双重检查（double checked lock），volatile避免重排序。
 * 只在instance对象初始化时同步
 * Created by XiangZhuRui on 2017/7/7.
 */
public class Singleton2 {
    private static volatile Singleton2 instance;

    private Singleton2() {
    }

    public static Singleton2 getInstance() {
        if (instance == null) {
            synchronized (Singleton2.class) {
                if (instance == null) {
                    instance = new Singleton2();
                }
            }
        }
        return instance;
    }
}
