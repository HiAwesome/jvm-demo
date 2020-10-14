package com.moqi.jvm.ch04;

/**
 * JConsole 测试 - 线程监控 - 死锁
 * <p>
 * -Xms100m -Xmx100m -XX:+UseSerialGC
 * <p>
 * 测试方法，运行时观察 main, 然后在控制台输入任意字符到下一步观察 testBusyThread, 最后在控制台输入任意字符到下一步观察 testLockThread
 *
 * @author moqi On 10/14/20 10:28
 */

public class A04MonitoringThreadDeadLockTest {

    /**
     * 线程死锁等待演示
     */
    static class SyncAddRunnable implements Runnable {
        int a, b;

        public SyncAddRunnable(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println("a + b = " + (a + b));
                }
            }
        }
    }

    /**
     * Name: Thread-174
     * State: BLOCKED on java.lang.Integer@7b05c277 owned by: Thread-173
     * Total blocked: 2  Total waited: 0
     *
     * Stack trace:
     * app//com.moqi.jvm.ch04.A04MonitoringThreadDeadLockTest$SyncAddRunnable.run(A04MonitoringThreadDeadLockTest.java:34)
     *    - locked java.lang.Integer@1424c34a
     * java.base@11.0.8/java.lang.Thread.run(Thread.java:834)
     *
     *
     *
     * Name: Thread-173
     * State: BLOCKED on java.lang.Integer@1424c34a owned by: Thread-174
     * Total blocked: 2  Total waited: 0
     *
     * Stack trace:
     * app//com.moqi.jvm.ch04.A04MonitoringThreadDeadLockTest$SyncAddRunnable.run(A04MonitoringThreadDeadLockTest.java:34)
     *    - locked java.lang.Integer@7b05c277
     * java.base@11.0.8/java.lang.Thread.run(Thread.java:834)
     */
    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new SyncAddRunnable(1, 2)).start();
            new Thread(new SyncAddRunnable(2, 1)).start();
        }
    }

}
