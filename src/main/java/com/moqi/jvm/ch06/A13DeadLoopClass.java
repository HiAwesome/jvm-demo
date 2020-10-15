package com.moqi.jvm.ch06;

/**
 * 多线程环境中阻塞执行 <clinit>() 方法
 *
 * @author moqi On 10/15/20 17:17
 */

public class A13DeadLoopClass {

    static class DeadLoopClass {

        static {
            // 如果不加上这个 if 语句，编译器将提示 Initializer does not complete normally 并拒绝编译
            if (true) {
                System.out.println(Thread.currentThread() + " init DeadLoopClass.");
                while (true);
            }
        }

    }

    /**
     * 一条线程在死循环以模拟长时间操作，另外一条线程在阻塞等待
     *
     * Thread[Thread-0,5,main] start.
     * Thread[Thread-1,5,main] start.
     * Thread[Thread-0,5,main] init DeadLoopClass.
     */
    public static void main(String[] args) {
        Runnable script = () -> {
            System.out.println(Thread.currentThread() + " start.");
            DeadLoopClass dlc = new DeadLoopClass();
            System.out.println(Thread.currentThread() + " run over.");
        };

        Thread t1 = new Thread(script);
        Thread t2 = new Thread(script);
        t1.start();
        t2.start();
    }

}
