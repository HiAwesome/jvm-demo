package com.moqi.jvm.ch04;

/**
 * -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 *
 * staticObj, instanceObj, localObj 存放在哪里？
 * 在测试的 openjdk10 中都存放在 Java 堆中，在 JDK 7 之前静态变量是存放在永久代上的
 *
 *
 * 测试 N 个 JDK 版本，没有实现书中效果，执行到 jhsbd hsbd --pid (pid) 时：
 * 大部分 JDK 报错是权限问题，无法启动，显示
 *   Unable to connect to process ID (pid):
 *   sun.jvm.hotspot.debugger.DebuggerException: Can't attach to the process. Could be caused by an incorrect pid or lack of privileges.
 *
 * openjdk10 和 openjdk9 可用
 *
 * hsdb> scanoops 0x000000011fa00000 0x0000000120400000 com.moqi.jvm.ch04.A01JHSDB_TestCase$ObjectHolder
 * 0x000000011faa2658 com/moqi/jvm/ch04/A01JHSDB_TestCase$ObjectHolder
 * 0x000000011faa2680 com/moqi/jvm/ch04/A01JHSDB_TestCase$ObjectHolder
 * 0x000000011faa2690 com/moqi/jvm/ch04/A01JHSDB_TestCase$ObjectHolder
 *
 * 其他参考，也进行了一些练习
 * 1. https://www.iteye.com/blog/rednaxelafx-1847971
 * 2. https://yuck1125.github.io/2019/10/17/use-HSDB-verify-Class-in-heap/
 *
 * @author moqi On 10/13/20 15:07
 */

public class A01JHSDB_TestCase {

    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            // 在这里设置一个断点
            System.out.println("Done");
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new A01JHSDB_TestCase.Test();
        test.foo();
    }
}
