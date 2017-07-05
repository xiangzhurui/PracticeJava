package basic.thread;

import java.util.Date;

public class TreadTest {
    public static void main(String[] args) {
        testStart();
//        testRun();
    }

    public static void testStart() {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                while(true)
                { 
                    if(Thread.currentThread().isInterrupted())
                    { 
                       System.out.println("Interruted!"+new Date()); 
                       break; 
                    } 
                    Thread.yield(); 
                } 
            }
        };
        thread.start();
        thread.interrupt();
    }
    public static void testRun() {
        Thread thread = new Thread("t1") {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                while(true)
                { 
                    if(Thread.currentThread().isInterrupted())
                    { 
                       System.out.println("Interruted!"); 
                       break; 
                    } 
                    Thread.yield(); 
                } 
            }
        };
        thread.run();
    }
}
