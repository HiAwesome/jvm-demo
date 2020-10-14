package com.moqi.jvm.ch04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * JConsole 测试 - 线程监控 - 活锁
 *
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 *
 * 测试方法，运行时观察 main, 然后在控制台输入任意字符到下一步观察 testBusyThread, 最后在控制台输入任意字符到下一步观察 testLockThread
 *
 * @author moqi On 10/14/20 10:28
 */

public class A03MonitoringThreadAliveLockTest {

    /**
     * 线程死循环演示
     */
    public static void createBusyThread() {
        Thread thread = new Thread(() -> {
            while (true);
        }, "testBusyThread");

        thread.start();
    }

    /**
     * 线程锁等待演示
     *
     * Name: testLockThread
     * State: WAITING on java.lang.Object@4bdb4e1e
     * Total blocked: 0  Total waited: 1
     *
     * Stack trace:
     * java.base@11.0.8/java.lang.Object.wait(Native Method)
     * java.base@11.0.8/java.lang.Object.wait(Object.java:328)
     * app//com.moqi.jvm.ch04.A03MonitoringThreadAliveLockTest.lambda$createLockThread$1(A03MonitoringThreadAliveLockTest.java:37)
     * app//com.moqi.jvm.ch04.A03MonitoringThreadAliveLockTest$$Lambda$99/0x00000008001a7c40.run(Unknown Source)
     * java.base@11.0.8/java.lang.Thread.run(Thread.java:834)
     */
    public static void createLockThread(final Object lock) {
        Thread thread = new Thread(() -> {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "testLockThread");
        thread.start();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        br.readLine();
        System.out.println("before run createBusyThread");
        createBusyThread();

        br.readLine();
        Object obj = new Object();
        System.out.println("before run createLockThread");
        createLockThread(obj);
    }

}
