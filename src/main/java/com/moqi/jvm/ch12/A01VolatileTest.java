package com.moqi.jvm.ch12;

/**
 * Volatile 变量自增运算测试
 *
 * @author moqi On 10/20/20 17:54
 */

public class A01VolatileTest {

    public static volatile int race = 0;

    public static void increase() {
        race++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];

        for (int i = 0; i < THREADS_COUNT; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    increase();
                }
            });

            threads[i].start();
        }

        // 加上这一段代码会卡住
        // 等待所有累加线程都结束
        // while (Thread.activeCount() > 1) {
        //     Thread.yield();
        // }

        System.out.println("race = " + race);
    }

}
