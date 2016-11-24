package framework.mybatis.utils;

import org.junit.Test;

public class SqlSessionFactorySingletonTest {

    @Test
    public void test() {
        final SqlSessionFactorySingleton instance = SqlSessionFactorySingleton.INSTANCE;
        instance.getFactory();
        Thread a = new Thread(new Runnable() {
            public void run() {
                instance.getFactory();
            }
        });
        Thread b = new Thread(new Runnable() {
            public void run() {
                instance.getFactory();
            }
        });
        a.start();
        b.start();
    }

}
