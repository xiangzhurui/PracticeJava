package basic.thread;

/**
 * Created by XiangZhuRui on 2017/7/5.
 */
public class DeadLockSample0 {
    static Object lockA = new Object(), lockB = new Object();

    /**
     * synchronized方式死锁：
     * 线程0获取lockA,在synchronized快里获取lockB;
     * 线程1获取lockB,然后再synchronized块里获取lockA
     */
    public void deadLock() {
        Thread thread0 = new Thread(() -> {
            synchronized (lockA) {
                System.out.println("获取到lockA:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(600);
                    System.out.println("线程0:" + Thread.currentThread().getName() + "sleep结束");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "开始尝试获取lockB!");
                synchronized (lockB) {
                    System.out.println(Thread.currentThread().getName() + "正在获取lockB!");
                }
            }
        });
        Thread thread1 = new Thread(() -> {
            synchronized (lockB) {
                System.out.println("获取到lockB:" + Thread.currentThread().getName());
                try {
                    Thread.sleep(600);
                    System.out.println("线程1:" + Thread.currentThread().getName() + "sleep结束");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "开始尝试获取lockA!");
                synchronized (lockA) {
                    System.out.println(Thread.currentThread().getName() + "正在获取lockA!");
                }
            }
        });

        thread0.start();
        thread1.start();
    }

    public static void main(String[] args) {
        new DeadLockSample0().deadLock();
    }
}
