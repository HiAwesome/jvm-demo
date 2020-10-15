package com.moqi.jvm.ch06;

/**
 * 被动引用类字段演示一：
 * 通过子类引用父类的静态字段，不会导致子类初始化
 *
 * 父类
 *
 * @author moqi On 10/15/20 15:09
 */

public class A04SuperClass {

    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

}
