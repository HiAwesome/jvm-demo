package com.moqi.jvm.ch04;

/**
 * -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 * 运行时选择 LTS 的 Java 11
 *
 * staticObj, instanceObj, localObj 存放在哪里？
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
