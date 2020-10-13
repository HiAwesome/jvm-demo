package com.moqi.jvm.ch04;

/**
 * -Xmx10m -XX:+UseSerialGC -XX:-UseCompressedOops
 *
 * staticObj, instanceObj, localObj 存放在哪里？
 *
 * 测试 N 个 JDK 版本，没有实现书中效果
 * 参考 https://yuck1125.github.io/2019/10/17/use-HSDB-verify-Class-in-heap/ 进行了一些练习
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
