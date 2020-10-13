package com.moqi.jvm.ch02;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 使用unsafe分配本机内存
 * VM Args：-Xmx20M -XX:MaxDirectMemorySize=10M
 *
 * Exception in thread "main" java.lang.OutOfMemoryError
 *   at sun.misc.Unsafe.allocateMemory(Native Method)
 *   at org.fenixsoft.oom.DMOOM.main(DMOOM.java:20)
 *
 * 复现书中异常失败，JDK 为 LTS 的 11
 *
 * 参考 https://blog.csdn.net/jameskaron/article/details/105416551 和 https://youtrack.jetbrains.com/issue/IDEA-180033
 * 将 Java Compile 设定为 11
 *
 * @author moqi
 * On 9/29/20 14:54
 */
public class A06DirectMemoryOOM {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws Exception {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);

        while (true) {
            unsafe.allocateMemory(_1MB);
        }

    }

}
